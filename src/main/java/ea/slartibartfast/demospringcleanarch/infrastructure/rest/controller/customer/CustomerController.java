package ea.slartibartfast.demospringcleanarch.infrastructure.rest.controller.customer;

import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCaseExecutor;
import ea.slartibartfast.demospringcleanarch.domain.usecase.customer.CreateCustomerUseCase;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.ApiResponse;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.AuthenticationResponse;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.SignInRequest;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.SignUpRequest;
import ea.slartibartfast.demospringcleanarch.infrastructure.usecase.security.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class CustomerController implements CustomerResource {
    private final UseCaseExecutor useCaseExecutor;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final CreateCustomerInputMapper createCustomerUseCaseInputMapper;
    private final AuthenticateCustomerUseCase authenticateCustomerUseCase;

    @Override
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest,
                                                                 HttpServletRequest httpServletRequest) {
        return useCaseExecutor.execute(
                createCustomerUseCase,
                createCustomerUseCaseInputMapper.map(signUpRequest),
                outputValues -> CreateCustomerUseCaseOutputMapper.map(outputValues.customer(), httpServletRequest));
    }

    @Override
    public ResponseEntity<AuthenticationResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return useCaseExecutor.execute(
                authenticateCustomerUseCase,
                AuthenticateCustomerUseCaseInputMapper.map(signInRequest),
                AuthenticateCustomerUseCaseOutputMapper::map);
    }
}