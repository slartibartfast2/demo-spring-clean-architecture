package ea.slartibartfast.demospringcleanarch.data.db.jpa.entity;


import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.OrderItem;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Entity(name = "orderItem")
@EqualsAndHashCode(of = {"order", "product", "price", "quantity", "total"})
@Getter
@NoArgsConstructor
@Setter
@Table(name = "order_item")
@ToString(of = {"order", "product", "price", "quantity", "total"})
public class OrderItemData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderData order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductData product;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double total;

    public OrderItem fromThis() {
        return new OrderItem(
                new Identity(id),
                quantity,
                product.fromThis(),
                total
        );
    }
}