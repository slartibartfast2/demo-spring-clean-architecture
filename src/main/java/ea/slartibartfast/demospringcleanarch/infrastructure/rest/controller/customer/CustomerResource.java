package ea.slartibartfast.demospringcleanarch.infrastructure.rest.controller.customer;


import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.ApiResponse;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.AuthenticationResponse;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.SignInRequest;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.SignUpRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/customer")
public interface CustomerResource {

    @PostMapping
    ResponseEntity<ApiResponse> signUp(
            @Valid @RequestBody SignUpRequest request, HttpServletRequest httpServletRequest);

    @PostMapping("/auth")
    ResponseEntity<AuthenticationResponse> signIn(@Valid @RequestBody SignInRequest request);
}