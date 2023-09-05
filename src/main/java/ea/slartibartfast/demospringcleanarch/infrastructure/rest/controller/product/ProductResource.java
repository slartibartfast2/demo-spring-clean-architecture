package ea.slartibartfast.demospringcleanarch.infrastructure.rest.controller.product;

import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.ProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public interface ProductResource {
    @GetMapping
    List<ProductResponse> getAllProducts();

    @GetMapping("/{id}")
    ProductResponse getByIdentity(@PathVariable Long id);

    @GetMapping("/search/{text}")
    List<ProductResponse> getByMatchingName(@PathVariable String text);
}
