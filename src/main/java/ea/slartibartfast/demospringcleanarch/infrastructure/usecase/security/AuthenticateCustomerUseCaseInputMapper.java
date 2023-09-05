package ea.slartibartfast.demospringcleanarch.infrastructure.usecase.security;


import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.SignInRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public final class AuthenticateCustomerUseCaseInputMapper {
    public static AuthenticateCustomerUseCase.InputValues map(SignInRequest signInRequest) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                signInRequest.email(),
                signInRequest.password());

        return new AuthenticateCustomerUseCase.InputValues(auth);
    }
}