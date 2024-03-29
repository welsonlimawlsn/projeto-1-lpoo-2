package br.com.welson.banco.comum;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEntity<ID> implements Serializable {

    public abstract ID getId();

    public abstract void setId(ID id);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
