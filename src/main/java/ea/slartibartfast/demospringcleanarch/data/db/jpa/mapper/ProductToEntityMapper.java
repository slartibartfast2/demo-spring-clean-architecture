package ea.slartibartfast.demospringcleanarch.data.db.jpa.mapper;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.ProductData;
import ea.slartibartfast.demospringcleanarch.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.IdConverter.convertId;

@RequiredArgsConstructor
@Component
public class ProductToEntityMapper implements Function<Product, ProductData> {

    private final StoreToEntityMapper storeToEntityMapper;

    @Override
    public ProductData apply(Product product) {
        return new ProductData(
                convertId(product.getId()),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                storeToEntityMapper.apply(product.getStore())
        );
    }
}
