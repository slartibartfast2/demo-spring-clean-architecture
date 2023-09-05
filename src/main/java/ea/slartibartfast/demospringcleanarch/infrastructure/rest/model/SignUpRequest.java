package ea.slartibartfast.demospringcleanarch.infrastructure.rest.model;

import ea.slartibartfast.demospringcleanarch.domain.usecase.customer.CreateCustomerUseCase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(@NotBlank @Size(min = 4, max = 50) String name,
                            @Email @NotBlank @Size(max = 100) String email, @NotBlank String address,
                            @NotBlank @Size(min = 6, max = 50) String password) {
    public static CreateCustomerUseCase.InputValues from(SignUpRequest signUpRequest) {
        return new CreateCustomerUseCase.InputValues(
                signUpRequest.name(),
                signUpRequest.email(),
                signUpRequest.address(),
                signUpRequest.password());
    }
}
