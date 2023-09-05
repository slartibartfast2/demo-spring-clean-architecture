package ea.slartibartfast.demospringcleanarch.domain.usecase.order;

import ea.slartibartfast.demospringcleanarch.domain.model.*;
import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCase;
import ea.slartibartfast.demospringcleanarch.domain.usecase.product.GetProductsByStoreAndProductsIdUseCase;
import ea.slartibartfast.demospringcleanarch.domain.usecase.product.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CreateOrderUseCase extends UseCase<CreateOrderUseCase.InputValues, CreateOrderUseCase.OutputValues> {

    private final GetProductsByStoreAndProductsIdUseCase getProductsByStoreAndProductsIdUseCase;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public OutputValues execute(InputValues input) {
        Order order = createOrder(input);

        updateStock(order);

        return new OutputValues(orderRepository.persist(order));
    }

    private Order createOrder(InputValues input) {
        final List<OrderItem> orderItems = createOrderItems(input);

        return Order.newInstance(
                Identity.nothing(),
                input.customer(),
                getFirstProductStore(orderItems),
                orderItems
        );
    }

    private void updateStock(Order order) {
        order.orderItems()
             .stream()
             .map(OrderItem::getProduct)
             .forEach(product -> productRepository.updateStock(product.getId(), product.getQuantity() - 1));
    }

    private Store getFirstProductStore(List<OrderItem> orderItems) {
        return orderItems.get(0).getProduct().getStore();
    }

    private List<OrderItem> createOrderItems(InputValues input) {
        Map<Identity, Product> productMap = getProducts(input);

        return input.orderItems()
                    .stream()
                    .map(inputItem -> createOrderItem(inputItem, productMap))
                    .toList();
    }

    private OrderItem createOrderItem(InputItem inputItem, Map<Identity, Product> productMap) {
        Product product = productMap.get(inputItem.id());
        return OrderItem.newInstance(Identity.nothing(), product, inputItem.quantity());
    }

    private Map<Identity, Product> getProducts(InputValues input) {
        GetProductsByStoreAndProductsIdUseCase.InputValues inputValues =
                new GetProductsByStoreAndProductsIdUseCase.InputValues(
                        input.storeId(), createListOfProductsId(input.orderItems()));

        return getProductsByStoreAndProductsIdUseCase.execute(inputValues)
                                                     .products()
                                                     .stream()
                                                     .collect(Collectors.toMap(Product::getId, Function.identity()));
    }

    private List<Identity> createListOfProductsId(List<InputItem> inputItems) {
        return inputItems
                .stream()
                .map(InputItem::id)
                .toList();
    }

    public record InputValues (Customer customer, Identity storeId, List<InputItem> orderItems) implements UseCase.InputValues {
    }

    public record OutputValues(Order order) implements UseCase.OutputValues {
    }

    public record InputItem(Identity id, int quantity) {
    }
}
