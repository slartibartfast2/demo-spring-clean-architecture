package ea.slartibartfast.demospringcleanarch.data.db.jpa.repository;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.ProductData;
import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Product;
import ea.slartibartfast.demospringcleanarch.domain.usecase.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository
                .findAll()
                .stream()
                .map(ProductData::fromThis)
                .toList();
    }

    @Override
    public Optional<Product> getById(Identity id) {
        return repository
                .findById(id.number())
                .map(ProductData::fromThis);
    }

    @Override
    public List<Product> searchByNameOrDescription(String searchText) {
        return repository
                .findByNameContainingOrDescriptionContainingAllIgnoreCase(searchText, searchText)
                .stream()
                .map(ProductData::fromThis)
                .toList();
    }

    @Override
    public List<Product> searchProductsByStoreAndProductsId(Identity storeId, List<Identity> productsId) {
        return repository
                .findByStoreIdAndIdIsIn(storeId.number(), createListOfLong(productsId))
                .stream()
                .map(ProductData::fromThis)
                .toList();
    }

    @Override
    public void updateStock(Identity id, Integer newStockQuantity) {
        repository.updateProductStockQuantity(id.number(), newStockQuantity);
    }

    private List<Long> createListOfLong(List<Identity> productsId) {
        return productsId
                .stream()
                .map(Identity::number)
                .toList();
    }
}
