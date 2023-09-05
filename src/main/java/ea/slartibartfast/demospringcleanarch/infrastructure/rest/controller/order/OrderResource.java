package ea.slartibartfast.demospringcleanarch.infrastructure.rest.controller.order;

import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.ApiResponse;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.CustomerResponse;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.OrderRequest;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.OrderResponse;
import ea.slartibartfast.demospringcleanarch.infrastructure.usecase.model.CurrentUser;
import ea.slartibartfast.demospringcleanarch.infrastructure.usecase.model.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/order")
public interface OrderResource {

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<ApiResponse> create(@CurrentUser UserPrincipal userPrincipal,
                                       HttpServletRequest httpServletRequest,
                                       @Valid @RequestBody OrderRequest orderRequest);

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    OrderResponse getById(@PathVariable Long id);

    @GetMapping("/{id}/customer")
    @PreAuthorize("hasRole('USER')")
    CustomerResponse getCustomerById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    ApiResponse delete(@PathVariable Long id);

    @PostMapping("/{id}/payment")
    @PreAuthorize("hasRole('USER')")
    ApiResponse pay(@PathVariable Long id);

    @PostMapping("/{id}/delivery")
    @PreAuthorize("hasRole('USER')")
    ApiResponse delivery(@PathVariable Long id);
}
