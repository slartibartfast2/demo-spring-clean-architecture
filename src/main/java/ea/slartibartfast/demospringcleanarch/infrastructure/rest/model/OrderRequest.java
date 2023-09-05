package ea.slartibartfast.demospringcleanarch.infrastructure.rest.controller.order.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Getter
public record OrderRequest(@NotNull Long storeId, @NotEmpty List<OrderRequestItem> orderItems) {

}
