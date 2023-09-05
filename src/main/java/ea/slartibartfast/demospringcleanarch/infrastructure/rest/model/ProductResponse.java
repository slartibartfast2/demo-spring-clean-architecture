package ea.slartibartfast.demospringcleanarch.infrastructure.rest.model;

import ea.slartibartfast.demospringcleanarch.domain.model.Product;

import java.util.List;

import static ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.IdConverter.convertId;

public record ProductResponse(Long id, String name, String description, Double price, Integer quantity, Long storeId) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                convertId(product.getId()),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                convertId(product.getStore().id()));
    }

    public static List<ProductResponse> from(List<Product> products) {
        return products.parallelStream()
                       .map(ProductResponse::from)
                       .toList();
    }
}
