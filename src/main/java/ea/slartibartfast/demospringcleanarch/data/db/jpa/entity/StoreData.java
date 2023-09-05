package ea.slartibartfast.demospringcleanarch.data.db.jpa.entity;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Store;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Entity(name = "store")
@EqualsAndHashCode(of = {"name", "address"})
@Getter
@NoArgsConstructor
@Setter
@Table(name = "store")
@ToString(of = {"name", "address"})
public class StoreData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "store")
    private Set<ProductData> products;

    public Store fromThis() {
        return new Store(
                new Identity(id),
                name,
                address
        );
    }
}
