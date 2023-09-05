package ea.slartibartfast.demospringcleanarch.infrastructure.rest.model;

import ea.slartibartfast.demospringcleanarch.domain.model.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public record OrderResponseItem(String name, Double price, Integer quantity, Double total) {
    public static List<OrderResponseItem> from(List<OrderItem> orderItems) {
        return orderItems
                .stream()
                .map(OrderResponseItem::from)
                .collect(Collectors.toList());
    }

    private static OrderResponseItem from(OrderItem orderItem) {
        return new OrderResponseItem(
                orderItem.getProduct().getName(),
                orderItem.getProduct().getPrice(),
                orderItem.getQuantity(),
                orderItem.getTotal()
        );
    }
}
