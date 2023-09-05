package ea.slartibartfast.demospringcleanarch.domain.usecases.customer;


import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.CustomerData;
import ea.slartibartfast.demospringcleanarch.domain.model.Customer;

import java.util.Optional;

public interface CustomerRepository {
    Customer persist(Customer customer);

    boolean existsByEmail(String email);

    Optional<CustomerData> findByEmail(String email);

    Optional<CustomerData> findById(Long id);
}