package ea.slartibartfast.demospringcleanarch.domain.usecase.customer;

import ea.slartibartfast.demospringcleanarch.domain.model.Customer;
import ea.slartibartfast.demospringcleanarch.domain.model.exception.ValidationException;
import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCustomerUseCase extends UseCase<CreateCustomerUseCase.InputValues, CreateCustomerUseCase.OutputValues> {

    private final CustomerRepository repository;

    @Override
    public OutputValues execute(InputValues input) {
        if (repository.existsByEmail(input.email())) {
            throw new ValidationException("Email address already in use!");
        }

        Customer customer = Customer.newInstance(
                input.name(),
                input.email(),
                input.address(),
                input.password()
        );

        return new OutputValues(repository.persist(customer));
    }

    public record InputValues(String name, String email, String address,
                              String password) implements UseCase.InputValues {
    }

    public record OutputValues(Customer customer) implements UseCase.OutputValues {
    }
}
