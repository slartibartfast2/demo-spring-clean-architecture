package ea.slartibartfast.demospringcleanarch.infrastructure.rest.model;

import ea.slartibartfast.demospringcleanarch.domain.model.Customer;

public record CustomerResponse(String name, String email, String address) {
    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.getName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}