package ea.slartibartfast.demospringcleanarch.infrastructure.rest.model;

import ea.slartibartfast.demospringcleanarch.domain.model.Store;

import java.util.List;

public record StoreResponse(Long id, String name, String address) {
    public static StoreResponse from(Store store) {
        return new StoreResponse(
                store.id().number(),
                store.name(),
                store.address()
        );
    }

    public static List<StoreResponse> from(List<Store> stores) {
        return stores
                .parallelStream()
                .map(StoreResponse::from)
                .toList();
    }
}
