package ea.slartibartfast.demospringcleanarch.domain.usecase.product;

import ea.slartibartfast.demospringcleanarch.domain.model.Product;
import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SearchProductsByNameOrDescriptionUseCase extends UseCase<SearchProductsByNameOrDescriptionUseCase.InputValues, SearchProductsByNameOrDescriptionUseCase.OutputValues> {

    private final ProductRepository repository;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.searchByNameOrDescription(input.searchText()));
    }

    public record InputValues(String searchText) implements UseCase.InputValues {
    }

    public record OutputValues(List<Product> products) implements UseCase.OutputValues {
    }
}
