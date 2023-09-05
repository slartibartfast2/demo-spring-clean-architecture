package ea.slartibartfast.demospringcleanarch.infrastructure.rest.model;

import ea.slartibartfast.demospringcleanarch.domain.model.Order;
import ea.slartibartfast.demospringcleanarch.domain.model.Status;

import java.time.Instant;
import java.util.List;

import static ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.IdConverter.convertId;

public record OrderResponse(Long id, Instant date, CustomerResponse customer, StoreResponse store, String contact,
                            Double total, Status status, Instant lastUpdate, List<OrderResponseItem> orderItems) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(
                convertId(order.id()),
                order.createdAt(),
                CustomerResponse.from(order.customer()),
                StoreResponse.from(order.store()),
                order.customer().getName(),
                order.total(),
                order.status(),
                order.updatedAt(),
                OrderResponseItem.from(order.orderItems())
        );
    }
}
