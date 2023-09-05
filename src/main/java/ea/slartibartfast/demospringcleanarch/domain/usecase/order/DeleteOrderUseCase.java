package ea.slartibartfast.demospringcleanarch.domain.usecase.order;

import ea.slartibartfast.demospringcleanarch.domain.model.Order;

public class DeleteOrderUseCase extends UpdateOrderUseCase {

    public DeleteOrderUseCase(OrderRepository repository) {
        super(repository);
    }

    @Override
    protected Order updateStatus(Order order) {
        return order.delete();
    }
}
