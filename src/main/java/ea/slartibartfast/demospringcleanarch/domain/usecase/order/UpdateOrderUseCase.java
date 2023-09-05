package ea.slartibartfast.demospringcleanarch.domain.usecases.order;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Order;
import ea.slartibartfast.demospringcleanarch.domain.model.exception.NotFoundException;
import ea.slartibartfast.demospringcleanarch.domain.usecases.UseCase;
import lombok.Getter;

public abstract class UpdateOrderUseCase extends UseCase<UpdateOrderUseCase.InputValues, UpdateOrderUseCase.OutputValues> {
    protected OrderRepository repository;

    protected UpdateOrderUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        final Identity id = input.id();

        return this.repository
                .getById(id)
                .map(this::updateStatus)
                .map(this::persistAndReturn)
                .orElseThrow(() -> new NotFoundException("Order %s not found", id));
    }

    protected abstract Order updateStatus(Order order);

    private OutputValues persistAndReturn(Order order) {
        return new OutputValues(this.repository.persist(order));
    }

    @Getter
    public record InputValues(Identity id) implements UseCase.InputValues {
    }

    public record OutputValues(Order order) implements UseCase.OutputValues {
    }
}
