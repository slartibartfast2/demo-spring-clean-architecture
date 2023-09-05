package ea.slartibartfast.demospringcleanarch.domain.usecases.order;


import ea.slartibartfast.demospringcleanarch.domain.model.Customer;
import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Order;
import ea.slartibartfast.demospringcleanarch.domain.usecases.UseCase;
import lombok.Getter;
import lombok.Value;

public class GetCustomerOrderUseCase extends UseCase<GetCustomerOrderUseCase.InputValues, GetCustomerOrderUseCase.OutputValues> {
    private GetOrderUseCase getOrderUseCase;

    public GetCustomerOrderUseCase(GetOrderUseCase getOrderUseCase) {
        this.getOrderUseCase = getOrderUseCase;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Order order = getOrderUseCase
                .execute(new GetOrderUseCase.InputValues(input.id()))
                .getOrder();

        return new OutputValues(order.getCustomer());
    }

    public record InputValues(Identity id) implements UseCase.InputValues {
    }

    @Getter
    public record OutputValues(Customer customer) implements UseCase.OutputValues {
    }
}