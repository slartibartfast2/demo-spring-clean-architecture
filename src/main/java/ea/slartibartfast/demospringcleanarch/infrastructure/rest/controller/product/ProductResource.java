package ea.slartibartfast.demospringcleanarch.infrastructure.rest.controller.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public interface ProductResource {
    @GetMapping
    CompletableFuture<List<ProductResponse>> getAllProducts();

    @GetMapping("/{id}")
    CompletableFuture<ProductResponse> getByIdentity(@PathVariable Long id);

    @GetMapping("/search/{text}")
    CompletableFuture<List<ProductResponse>> getByMatchingName(@PathVariable String text);
}
