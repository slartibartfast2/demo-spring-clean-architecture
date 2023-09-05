package ea.slartibartfast.demospringcleanarch.data.db.jpa.repository;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCustomerRepository extends JpaRepository<CustomerData, Long> {
    boolean existsByEmail(String email);

    Optional<CustomerData> findByEmail(String email);
}
