package ea.slartibartfast.demospringcleanarch.infrastructure.rest.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(@NotNull Long storeId, @NotEmpty List<OrderRequestItem> orderItems) {

}
