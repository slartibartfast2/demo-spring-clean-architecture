package ea.slartibartfast.demospringcleanarch.infrastructure.rest.controller.product;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCaseExecutor;
import ea.slartibartfast.demospringcleanarch.domain.usecase.product.GetAllProductsUseCase;
import ea.slartibartfast.demospringcleanarch.domain.usecase.product.GetProductUseCase;
import ea.slartibartfast.demospringcleanarch.domain.usecase.product.SearchProductsByNameOrDescriptionUseCase;
import ea.slartibartfast.demospringcleanarch.infrastructure.rest.model.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController implements ProductResource {

    private final UseCaseExecutor useCaseExecutor;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductUseCase getProductUseCase;
    private final SearchProductsByNameOrDescriptionUseCase searchProductsByNameOrDescriptionUseCase;

    @Override
    public List<ProductResponse> getAllProducts() {
        return useCaseExecutor.execute(
                getAllProductsUseCase,
                new GetAllProductsUseCase.InputValues(),
                outputValues -> ProductResponse.from(outputValues.products()));
    }

    @Override
    public ProductResponse getByIdentity(@PathVariable Long id) {
        return useCaseExecutor.execute(
                getProductUseCase,
                new GetProductUseCase.InputValues(new Identity(id)),
                outputValues -> ProductResponse.from(outputValues.product()));
    }

    @Override
    public List<ProductResponse> getByMatchingName(@PathVariable String text) {
        return useCaseExecutor.execute(
                searchProductsByNameOrDescriptionUseCase,
                new SearchProductsByNameOrDescriptionUseCase.InputValues(text),
                outputValues -> ProductResponse.from(outputValues.products()));
    }
}
