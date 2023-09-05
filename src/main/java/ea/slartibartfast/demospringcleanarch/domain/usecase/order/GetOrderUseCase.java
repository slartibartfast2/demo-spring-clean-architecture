package ea.slartibartfast.demospringcleanarch.domain.usecase.order;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Order;
import ea.slartibartfast.demospringcleanarch.domain.model.exception.NotFoundException;
import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCase;

public class GetOrderUseCase extends UseCase<GetOrderUseCase.InputValues, GetOrderUseCase.OutputValues> {
    private OrderRepository repository;

    public GetOrderUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        final Identity id = input.id();

        return repository.getById(id)
                         .map(OutputValues::new)
                         .orElseThrow(() -> new NotFoundException("Order %s not found", id.number()));
    }

    public record InputValues(Identity id) implements UseCase.InputValues {
    }

    public record OutputValues(Order order) implements UseCase.OutputValues {
    }
}
