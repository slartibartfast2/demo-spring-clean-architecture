package ea.slartibartfast.demospringcleanarch.data.db.jpa.entity;

import ea.slartibartfast.demospringcleanarch.domain.model.Identity;

public final class IdConverter {

    public static Long convertId(Identity id) {
        if (id != null && id.number() != Long.MIN_VALUE) {
            return id.number();
        }

        return null;
    }
}
