package ea.slartibartfast.demospringcleanarch.domain.usecase.product;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Product;
import ea.slartibartfast.demospringcleanarch.domain.model.exception.NotFoundException;
import ea.slartibartfast.demospringcleanarch.domain.usecase.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetProductUseCase extends UseCase<GetProductUseCase.InputValues, GetProductUseCase.OutputValues> {

    private final ProductRepository repository;

    @Override
    public OutputValues execute(InputValues input) {
        Identity id = input.id;

        return repository
                .getById(id)
                .map(OutputValues::new)
                .orElseThrow(() -> new NotFoundException("Product %s not found", id.number()));
    }

    public record InputValues(Identity id) implements UseCase.InputValues {
    }

    public record OutputValues(Product product) implements UseCase.OutputValues {
    }
}