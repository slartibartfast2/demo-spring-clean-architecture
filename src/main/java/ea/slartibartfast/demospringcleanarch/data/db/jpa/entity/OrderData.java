package ea.slartibartfast.demospringcleanarch.data.db.jpa.entity;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Order;
import ea.slartibartfast.demospringcleanarch.domain.model.OrderItem;
import ea.slartibartfast.demospringcleanarch.domain.model.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity(name = "order")
@EqualsAndHashCode(of = {"customer", "store", "total", "status", "createdAt", "updatedAt"})
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "orders")
@ToString(of = {"customer", "store", "total", "status", "createdAt", "updatedAt"})
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerData customer;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreData store;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "order",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            orphanRemoval = true
    )
    private Set<OrderItemData> orderItems;

    @Column(nullable = false)
    private Double total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public void addOrderItem(OrderItemData orderItem) {
        if (this.orderItems == null) {
            this.orderItems = new HashSet<>();
        }

        orderItem.setOrder(this);
        this.orderItems.add(orderItem);

        this.calculateTotal();
    }

    public Order fromThis() {
        return new Order(
                new Identity(id),
                status,
                customer.fromThis(),
                store.fromThis(),
                fromOrderItemData(),
                total,
                createdAt,
                updatedAt
        );
    }

    private List<OrderItem> fromOrderItemData() {
        return orderItems
                .stream()
                .map(OrderItemData::fromThis)
                .toList();
    }

    private void calculateTotal() {
        this.total = this.orderItems
                .stream()
                .mapToDouble(OrderItemData::getTotal)
                .sum();
    }
}
