package ea.slartibartfast.demospringcleanarch.infrastructure.usecase.security;

import ea.slartibartfast.demospringcleanarch.domain.model.Customer;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public final class CreateCustomerUseCaseOutputMapper {
    public static ResponseEntity<ApiResponse> map(Customer customer, HttpServletRequest httpServletRequest) {
        URI location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path("/customer/{id}")
                .buildAndExpand(customer.getId().number())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "registered successfully"));
    }
}