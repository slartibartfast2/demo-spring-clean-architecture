package ea.slartibartfast.demospringcleanarch.infrastructure.rest.controller.order;

import ea.slartibartfast.demospringcleanarch.domain.model.Order;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public final class CreateOrderOutputMapper {

    public static ResponseEntity<ApiResponse> map(Order order, HttpServletRequest httpServletRequest) {
        URI location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path("/order/{id}")
                .buildAndExpand(order.id().number())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "order created successfully"));
    }
}
