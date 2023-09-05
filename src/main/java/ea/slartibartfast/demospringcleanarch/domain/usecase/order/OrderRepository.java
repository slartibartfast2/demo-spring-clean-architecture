package ea.slartibartfast.demospringcleanarch.domain.usecases.order;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Order;

import java.util.Optional;

public interface OrderRepository {

    Order persist(Order order);

    Optional<Order> getById(Identity id);
}
