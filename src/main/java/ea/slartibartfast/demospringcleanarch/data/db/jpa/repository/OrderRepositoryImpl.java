package ea.slartibartfast.demospringcleanarch.data.db.jpa.repository;


import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.OrderData;
import ea.slartibartfast.demospringcleanarch.data.db.jpa.mapper.OrderToEntityMapper;
import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Order;
import ea.slartibartfast.demospringcleanarch.domain.usecase.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository repository;
    private final OrderToEntityMapper orderToEntityMapper;

    @Override
    public Order persist(Order order) {
        var orderData = orderToEntityMapper.apply(order);

        return repository.save(orderData).fromThis();
    }

    @Override
    public Optional<Order> getById(Identity id) {
        return repository
                .findById(id.number())
                .map(OrderData::fromThis);
    }
}
