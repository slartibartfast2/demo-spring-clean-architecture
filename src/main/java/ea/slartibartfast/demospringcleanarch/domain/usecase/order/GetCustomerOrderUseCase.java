package ea.slartibartfast.demospringcleanarch.domain.usecase.order;


import ea.slartibartfast.demospringcleanarch.domain.model.Customer;
import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Order;
import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCase;

public class GetCustomerOrderUseCase extends UseCase<GetCustomerOrderUseCase.InputValues, GetCustomerOrderUseCase.OutputValues> {
    private GetOrderUseCase getOrderUseCase;

    public GetCustomerOrderUseCase(GetOrderUseCase getOrderUseCase) {
        this.getOrderUseCase = getOrderUseCase;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Order order = getOrderUseCase
                .execute(new GetOrderUseCase.InputValues(input.id()))
                .order();

        return new OutputValues(order.customer());
    }

    public record InputValues(Identity id) implements UseCase.InputValues {
    }

    public record OutputValues(Customer customer) implements UseCase.OutputValues {
    }
}