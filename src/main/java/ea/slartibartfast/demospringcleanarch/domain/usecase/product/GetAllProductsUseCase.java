package ea.slartibartfast.demospringcleanarch.domain.usecase.product;

import ea.slartibartfast.demospringcleanarch.domain.model.Product;
import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllProductsUseCase extends UseCase<GetAllProductsUseCase.InputValues, GetAllProductsUseCase.OutputValues> {

    private final ProductRepository repository;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.getAll());
    }

    public static class InputValues implements UseCase.InputValues {
    }

    public record OutputValues(List<Product> products) implements UseCase.OutputValues {
    }
}