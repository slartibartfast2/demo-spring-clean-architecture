package ea.slartibartfast.demospringcleanarch.data.db.jpa.mapper;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.OrderItemData;
import ea.slartibartfast.demospringcleanarch.domain.model.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.IdConverter.convertId;

@RequiredArgsConstructor
@Component
public class OrderItemToEntityMapper implements Function<OrderItem, OrderItemData> {

    private final ProductToEntityMapper productToEntityMapper;

    @Override
    public OrderItemData apply(OrderItem orderItem) {
        return new OrderItemData(
                convertId(orderItem.getId()),
                null,
                productToEntityMapper.apply(orderItem.getProduct()),
                orderItem.getProduct().getPrice(),
                orderItem.getQuantity(),
                orderItem.getTotal()
        );
    }
}
