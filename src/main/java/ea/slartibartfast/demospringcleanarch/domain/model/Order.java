package ea.slartibartfast.demospringcleanarch.domain.model;

import java.time.Instant;
import java.util.List;

public record Order(Identity id, Status status, Customer customer, Store store, List<OrderItem> orderItems,
                    Double total, Instant createdAt, Instant updatedAt) {
    public static Order newInstance(Identity id, Customer customer, Store store, List<OrderItem> orderItems) {
        return new Order(
                id,
                Status.OPEN,
                customer,
                store,
                orderItems,
                calculateTotal(orderItems),
                Instant.now(),
                Instant.now()
        );
    }

    private static Double calculateTotal(List<OrderItem> orderItems) {
        return orderItems
                .stream()
                .mapToDouble(OrderItem::getTotal)
                .sum();
    }

    public Order delete() {
        if (this.status != Status.OPEN) {
            throw new IllegalStateException("Order should be open to be cancelled");
        }

        return newInstanceWith(Status.CANCELLED);
    }

    public Order delivery() {
        if (this.status != Status.PAID) {
            throw new IllegalStateException("Order should be paid to be delivered");
        }

        return newInstanceWith(Status.DELIVERED);
    }

    public Order pay() {
        if (this.status != Status.OPEN) {
            throw new IllegalStateException("Order should be open to be paid");
        }

        return newInstanceWith(Status.PAID);
    }

    private Order newInstanceWith(Status status) {
        return new Order(
                id,
                status,
                customer,
                store,
                orderItems,
                total,
                createdAt,
                Instant.now()
        );
    }
}