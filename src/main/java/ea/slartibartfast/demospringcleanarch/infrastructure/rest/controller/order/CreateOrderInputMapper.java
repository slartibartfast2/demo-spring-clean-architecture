package ea.slartibartfast.demospringcleanarch.infrastructure.rest.controller.order;

import ea.slartibartfast.demospringcleanarch.domain.model.Customer;
import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.usecase.order.CreateOrderUseCase;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.OrderRequest;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.OrderRequestItem;
import ea.slartibartfast.demospringcleanarch.infrastructure.usecase.model.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public final class CreateOrderInputMapper {

    public static CreateOrderUseCase.InputValues map(OrderRequest orderRequest, UserDetails userDetails) {
        return new CreateOrderUseCase.InputValues(
                map(userDetails),
                new Identity(orderRequest.storeId()),
                map(orderRequest.orderItems())
        );
    }

    public static Customer map(UserDetails userDetails) {
        UserPrincipal userPrincipal = (UserPrincipal) userDetails;

        return new Customer(
                new Identity(userPrincipal.getId()),
                userPrincipal.getUsername(),
                userPrincipal.getEmail(),
                userPrincipal.getAddress(),
                userPrincipal.getPassword()
        );
    }

    private static List<CreateOrderUseCase.InputItem> map(List<OrderRequestItem> orderItems) {
        return orderItems
                .parallelStream()
                .map(CreateOrderInputMapper::map)
                .toList();
    }

    private static CreateOrderUseCase.InputItem map(OrderRequestItem orderRequestItem) {
        return new CreateOrderUseCase.InputItem(new Identity(orderRequestItem.id()), orderRequestItem.quantity());
    }
}