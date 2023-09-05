package ea.slartibartfast.demospringcleanarch.domain.usecases.product;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;
import ea.slartibartfast.demospringcleanarch.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();

    Optional<Product> getById(Identity id);

    List<Product> searchByNameOrDescription(String searchText);

    List<Product> searchProductsByStoreAndProductsId(Identity storeId, List<Identity> productsId);
}
