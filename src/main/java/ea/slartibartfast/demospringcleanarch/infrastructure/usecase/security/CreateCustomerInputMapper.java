package ea.slartibartfast.demospringcleanarch.infrastructure.usecase.security;

import ea.slartibartfast.demospringcleanarch.domain.usecase.customer.CreateCustomerUseCase;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCustomerInputMapper {

    private final PasswordEncoder passwordEncoder;

    public CreateCustomerUseCase.InputValues map(SignUpRequest signUpRequest) {
        return new CreateCustomerUseCase.InputValues(
                signUpRequest.name(),
                signUpRequest.email(),
                signUpRequest.address(),
                passwordEncoder.encode(signUpRequest.password()));
    }
}
