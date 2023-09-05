package ea.slartibartfast.demospringcleanarch.domain.usecases.product;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.exception.NotFoundException;
import ea.slartibartfast.demospringcleanarch.domain.model.Product;
import ea.slartibartfast.demospringcleanarch.domain.usecases.UseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class GetProductsByStoreAndProductsIdUseCase extends UseCase<GetProductsByStoreAndProductsIdUseCase.InputValues, GetProductsByStoreAndProductsIdUseCase.OutputValues> {

    private final ProductRepository repository;

    @Override
    public OutputValues execute(InputValues input) {
        final List<Identity> distinctProductsId = distinctIds(input.productsId());

        List<Product> foundProducts = repository
                .searchProductsByStoreAndProductsId(input.storeId(), distinctProductsId);

        throwIfAnyProductIsNotFound(distinctProductsId, foundProducts);

        return new OutputValues(foundProducts);
    }

    private void throwIfAnyProductIsNotFound(List<Identity> distinctProductsId,
                                             List<Product> foundProducts) {
        if (distinctProductsId.size() != foundProducts.size()) {
            final String message = createErrorMessage(distinctProductsId, foundProducts);
            throw new NotFoundException(message);
        }
    }

    private String createErrorMessage(List<Identity> distinctProductsId, List<Product> foundProducts) {
        List<String> missingProductsId = getMissingProductsId(distinctProductsId, foundProducts);
        return String.format("Product(s) %s not found", String.join(", ", missingProductsId));
    }

    private List<String> getMissingProductsId(List<Identity> distinctProductsId, List<Product> foundProducts) {
        Set<Long> distinctProductsIdSet = createDistinctProductsIdSet(distinctProductsId);
        Set<Long> foundProductsId = createFoundProductsIdSet(foundProducts);
        distinctProductsIdSet.removeAll(foundProductsId);

        return distinctProductsIdSet
                .stream()
                .map(Object::toString)
                .toList();
    }

    private Set<Long> createFoundProductsIdSet(List<Product> foundProducts) {
        return foundProducts
                .stream()
                .map(Product::getId)
                .map(Identity::getNumber)
                .collect(Collectors.toSet());
    }

    private Set<Long> createDistinctProductsIdSet(List<Identity> distinctProductsId) {
        return distinctProductsId
                .stream()
                .map(Identity::getNumber)
                .collect(Collectors.toSet());
    }

    private List<Identity> distinctIds(List<Identity> identities) {
        return identities
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public record InputValues(Identity storeId, List<Identity> productsId) implements UseCase.InputValues {
    }

    @Getter
    public record OutputValues(List<Product> products) implements UseCase.OutputValues {
    }
}