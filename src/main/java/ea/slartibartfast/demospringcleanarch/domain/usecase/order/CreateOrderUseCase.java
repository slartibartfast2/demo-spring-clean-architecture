package ea.slartibartfast.demospringcleanarch.domain.usecases.order;

import ea.slartibartfast.demospringcleanarch.domain.model.*;
import ea.slartibartfast.demospringcleanarch.domain.usecases.UseCase;
import ea.slartibartfast.demospringcleanarch.domain.usecases.product.GetProductsByStoreAndProductsIdUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CreateOrderUseCase extends UseCase<CreateOrderUseCase.InputValues, CreateOrderUseCase.OutputValues> {

    private final GetProductsByStoreAndProductsIdUseCase getProductsByStoreAndProductsIdUseCase;
    private final OrderRepository orderRepository;


    @Override
    public OutputValues execute(InputValues input) {
        Order order = createOrder(input);

        return new OutputValues(orderRepository.persist(order));
    }

    private Order createOrder(InputValues input) {
        final List<OrderItem> orderItems = createOrderItems(input);

        return Order.newInstance(
                Identity.nothing(),
                input.getCustomer(),
                getFirstProductStore(orderItems),
                orderItems
        );
    }

    private Store getFirstProductStore(List<OrderItem> orderItems) {
        return orderItems.get(0).getProduct().getStore();
    }

    private List<OrderItem> createOrderItems(InputValues input) {
        Map<Identity, Product> productMap = getProducts(input);

        return input
                .getOrderItems()
                .stream()
                .map(inputItem -> createOrderItem(inputItem, productMap))
                .collect(Collectors.toList());
    }

    private OrderItem createOrderItem(InputItem inputItem, Map<Identity, Product> productMap) {
        Product product = productMap.get(inputItem.getId());
        return OrderItem.newInstance(Identity.nothing(), product, inputItem.getQuantity());
    }

    private Map<Identity, Product> getProducts(InputValues input) {
        GetProductsByStoreAndProductsIdUseCase.InputValues inputValues =
                new GetProductsByStoreAndProductsIdUseCase.InputValues(
                        input.getStoreId(), createListOfProductsId(input.getOrderItems()));

        return getProductsByStoreAndProductsIdUseCase.execute(inputValues)
                                                     .products()
                                                     .stream()
                                                     .collect(Collectors.toMap(Product::getId, Function.identity()));
    }

    private List<Identity> createListOfProductsId(List<InputItem> inputItems) {
        return inputItems
                .stream()
                .map(InputItem::getId)
                .collect(Collectors.toList());
    }

    @Getter
    public record InputValues (Customer customer, Identity storeId, List<InputItem> orderItems) implements UseCase.InputValues {
    }

    @Setter
    @Getter
    public record OutputValues(Order order) implements UseCase.OutputValues {
    }

    @Getter
    public record InputItem(Identity id, int quantity) {
    }
}
