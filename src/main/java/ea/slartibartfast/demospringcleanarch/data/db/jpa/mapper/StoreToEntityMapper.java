package ea.slartibartfast.demospringcleanarch.data.db.jpa.mapper;

import ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.StoreData;
import ea.slartibartfast.demospringcleanarch.domain.model.Store;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.function.Function;

import static ea.slartibartfast.demospringcleanarch.data.db.jpa.entity.IdConverter.convertId;

@Component
public class StoreToEntityMapper implements Function<Store, StoreData> {
    @Override
    public StoreData apply(Store store) {
        return new StoreData(
                convertId(store.id()),
                store.name(),
                store.address(),
                new HashSet<>());
    }
}
