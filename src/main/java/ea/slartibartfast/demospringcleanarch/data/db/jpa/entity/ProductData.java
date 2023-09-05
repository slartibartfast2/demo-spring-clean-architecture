package ea.slartibartfast.demospringcleanarch.data.db.jpa.entity;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Product;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@Entity(name = "product")
@EqualsAndHashCode(of = {"name", "description", "price"})
@Getter
@NoArgsConstructor
@Setter
@Table(name = "product")
@ToString(of = {"name", "description", "price"})
public class ProductData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private StoreData store;

    public Product fromThis() {
        return new Product(
                new Identity(id),
                name,
                description,
                price,
                quantity,
                store.fromThis()
        );
    }
}
