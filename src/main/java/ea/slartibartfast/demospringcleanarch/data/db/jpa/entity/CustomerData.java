package ea.slartibartfast.demospringcleanarch.data.db.jpa.entity;

import ea.slartibartfast.demospringcleanarch.domain.model.Customer;
import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Entity(name = "customer")
@EqualsAndHashCode(of = {"name", "email", "address", "password"})
@Getter
@NoArgsConstructor
@Setter
@Table(name = "customer")
@ToString(of = {"name", "email", "address"})
public class CustomerData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    public Customer fromThis() {
        return new Customer(
                new Identity(id),
                name,
                email,
                address,
                password
        );
    }
}
