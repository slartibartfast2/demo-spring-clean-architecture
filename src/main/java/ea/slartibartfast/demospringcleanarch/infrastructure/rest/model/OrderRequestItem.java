package ea.slartibartfast.demospringcleanarch.infrastructure.rest.controller.order.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public record OrderRequestItem(@NotNull Long id, @Min(1) @NotNull Integer quantity) {
}