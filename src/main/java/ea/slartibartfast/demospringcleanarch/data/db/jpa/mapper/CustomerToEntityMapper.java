package ea.slartibartfast.demospringcleanarch.data.db.jpa.mapper;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.CustomerData;
import ea.slartibartfast.demospringcleanarch.domain.model.Customer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.IdConverter.convertId;

@Component
public class CustomerToEntityMapper implements Function<Customer, CustomerData> {

    @Override
    public CustomerData apply(Customer customer) {
        return new CustomerData(convertId(customer.getId()),
                                customer.getName(),
                                customer.getEmail(),
                                customer.getAddress(),
                                customer.getPassword()
        );
    }
}
