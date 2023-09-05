package ea.slartibartfast.demospringcleanarch.infrastructure.rest.controller.order;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCaseExecutor;
import ea.slartibartfast.demospringcleanarch.domain.usecase.order.*;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.ApiResponse;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.CustomerResponse;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.OrderRequest;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.OrderResponse;
import ea.slartibartfast.demospringcleanarch.infrastructure.usecase.model.CurrentUser;
import ea.slartibartfast.demospringcleanarch.infrastructure.usecase.model.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController implements OrderResource {
    private final UseCaseExecutor useCaseExecutor;
    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final GetCustomerOrderUseCase getCustomerOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;
    private final PayOrderUseCase payOrderUseCase;
    private final DeliveryOrderUseCase deliveryOrderUseCase;

    @Override
    public ResponseEntity<ApiResponse> create(@CurrentUser UserPrincipal userDetails,
                                              HttpServletRequest httpServletRequest,
                                              @Valid @RequestBody OrderRequest orderRequest) {
        return useCaseExecutor.execute(
                createOrderUseCase,
                CreateOrderInputMapper.map(orderRequest, userDetails),
                outputValues -> CreateOrderOutputMapper.map(outputValues.order(), httpServletRequest)
        );
    }

    @Override
    public OrderResponse getById(@PathVariable Long id) {
        return useCaseExecutor.execute(
                getOrderUseCase,
                new GetOrderUseCase.InputValues(new Identity(id)),
                outputValues -> OrderResponse.from(outputValues.order())
        );
    }

    @Override
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        return useCaseExecutor.execute(
                getCustomerOrderUseCase,
                new GetCustomerOrderUseCase.InputValues(new Identity(id)),
                outputValues -> CustomerResponse.from(outputValues.customer())
        );
    }

    @Override
    public ApiResponse delete(@PathVariable Long id) {
        return useCaseExecutor.execute(
                deleteOrderUseCase,
                new DeleteOrderUseCase.InputValues(new Identity(id)),
                (outputValues) -> new ApiResponse(true, "Order successfully canceled")
        );
    }

    @Override
    public ApiResponse pay(@PathVariable Long id) {
        return useCaseExecutor.execute(
                payOrderUseCase,
                new DeleteOrderUseCase.InputValues(new Identity(id)),
                (outputValues) -> new ApiResponse(true, "Order successfully paid")
        );
    }

    @Override
    public ApiResponse delivery(@PathVariable Long id) {
        return useCaseExecutor.execute(
                deliveryOrderUseCase,
                new DeleteOrderUseCase.InputValues(new Identity(id)),
                (outputValues) -> new ApiResponse(true, "Order successfully delivered")
        );
    }
}
