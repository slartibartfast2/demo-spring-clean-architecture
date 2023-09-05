package ea.slartibartfast.demospringcleanarch.domain.model;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Product {
    private Identity id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Store store;
}
