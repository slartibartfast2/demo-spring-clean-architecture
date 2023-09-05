package ea.slartibartfast.demospringcleanarch.data.db.jpa.mapper;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.OrderData;
import ea.slartibartfast.demospringcleanarch.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.function.Function;

import static ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.IdConverter.convertId;

@RequiredArgsConstructor
@Component
public class OrderToEntityMapper implements Function<Order, OrderData> {

    private final OrderItemToEntityMapper orderItemToEntityMapper;
    private final CustomerToEntityMapper customerToEntityMapper;
    private final StoreToEntityMapper storeToEntityMapper;

    @Override
    public OrderData apply(Order order) {
        OrderData orderData = new OrderData(
                convertId(order.id()),
                customerToEntityMapper.apply(order.customer()),
                storeToEntityMapper.apply(order.store()),
                new HashSet<>(),
                order.total(),
                order.status(),
                order.createdAt(),
                order.updatedAt()
        );

        order.orderItems()
             .stream()
             .map(orderItemToEntityMapper)
             .forEach(orderData::addOrderItem);

        return orderData;
    }
}
