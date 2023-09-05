package ea.slartibartfast.demospringcleanarch.infrastructure.usecase.security;

import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticateCustomerUseCase extends UseCase<AuthenticateCustomerUseCase.InputValues, AuthenticateCustomerUseCase.OutputValues> {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public OutputValues execute(InputValues input) {
        Authentication authentication = authenticationManager.authenticate(input.authenticationToken());
        log.info("User authenticated.");

        return new OutputValues(jwtService.generateToken(authentication));
    }

    public record InputValues(UsernamePasswordAuthenticationToken authenticationToken) implements UseCase.InputValues {
    }

    public record OutputValues(String jwtToken) implements UseCase.OutputValues {
    }
}
