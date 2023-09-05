package ea.slartibartfast.demospringcleanarch.data.db.jpa.repository;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderData, Long> {
}
