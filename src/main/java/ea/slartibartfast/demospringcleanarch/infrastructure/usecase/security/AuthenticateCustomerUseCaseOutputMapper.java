package ea.slartibartfast.demospringcleanarch.infrastructure.usecase.security;

import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.AuthenticationResponse;
import org.springframework.http.ResponseEntity;

public final class AuthenticateCustomerUseCaseOutputMapper {
    public static ResponseEntity<AuthenticationResponse> map(AuthenticateCustomerUseCase.OutputValues outputValues) {
        return ResponseEntity.ok(new AuthenticationResponse(outputValues.jwtToken()));
    }
}
