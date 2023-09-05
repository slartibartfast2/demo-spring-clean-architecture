package ea.slartibartfast.demospringcleanarch.domain.usecases.order;

import ea.slartibartfast.demospringcleanarch.domain.model.Order;

public class PayOrderUseCase extends UpdateOrderUseCase {
    public PayOrderUseCase(OrderRepository repository) {
        super(repository);
    }

    @Override
    protected Order updateStatus(Order order) {
        return repository.persist(order.pay());
    }
}
