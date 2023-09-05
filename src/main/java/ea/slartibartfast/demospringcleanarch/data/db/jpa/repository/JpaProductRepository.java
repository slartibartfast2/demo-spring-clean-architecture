package ea.slartibartfast.demospringcleanarch.data.db.jpa.repository;


import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<ProductData, Long> {
    List<ProductData> findByNameContainingOrDescriptionContainingAllIgnoreCase(String name, String description);

    List<ProductData> findByStoreIdAndIdIsIn(Long id, List<Long> ids);

    @Modifying
    @Query("update product p set p.quantity = :newStockQuantity where p.id = :id")
    void updateProductStockQuantity(@Param("id") Long id,
                                    @Param("newStockQuantity") Integer newStockQuantity);
}