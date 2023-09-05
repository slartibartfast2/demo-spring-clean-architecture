package ea.slartibartfast.demospringcleanarch.data.db.jpa.mapper;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.CustomerData;
import ea.slartibartfast.demospringcleanarch.domain.model.Customer;

import java.util.function.Function;

public class CustomerMapper implements Function<Customer, CustomerData> {
    @Override
    public CustomerData apply(Customer customer) {
        return null;
    }
}
