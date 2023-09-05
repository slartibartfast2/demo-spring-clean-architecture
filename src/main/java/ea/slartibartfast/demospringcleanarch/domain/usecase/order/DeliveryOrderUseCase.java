package ea.slartibartfast.demospringcleanarch.domain.usecases.order;

import ea.slartibartfast.demospringcleanarch.domain.model.Order;

public class DeliveryOrderUseCase extends UpdateOrderUseCase {
    public DeliveryOrderUseCase(OrderRepository repository) {
        super(repository);
    }

    @Override
    protected Order updateStatus(Order order) {
        order.delivery();

        return repository.persist(order.delivery());
    }
}
