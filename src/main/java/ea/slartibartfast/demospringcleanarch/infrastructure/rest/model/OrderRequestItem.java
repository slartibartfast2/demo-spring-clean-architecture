package ea.slartibartfast.demospringcleanarch.infrastructure.rest.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderRequestItem(@NotNull Long id, @Min(1) @NotNull Integer quantity) {
}