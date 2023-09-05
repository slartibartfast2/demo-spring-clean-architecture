package ea.slartibartfast.demospringcleanarch.data.db.jpa.repository;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.CustomerData;
import ea.slartibartfast.demospringcleanarch.data.db.jpa.mapper.CustomerToEntityMapper;
import ea.slartibartfast.demospringcleanarch.domain.model.Customer;
import ea.slartibartfast.demospringcleanarch.domain.usecase.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JpaCustomerRepository repository;
    private final CustomerToEntityMapper customerToEntityMapper;


    @Override
    public Customer persist(Customer customer) {
        final var customerData = customerToEntityMapper.apply(customer);
        return repository.save(customerData).fromThis();
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Optional<CustomerData> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<CustomerData> findById(Long id) {
        return repository.findById(id);
    }
}
