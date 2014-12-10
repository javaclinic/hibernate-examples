package example.hibernate.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Super class for all identifiable entities, i.e. the entities with identified by 'id'
 * 
 * @author nevenc
 *
 */
@MappedSuperclass
public class IdentifiableEntity implements Serializable {

    private static final long serialVersionUID = -2023419456023168468L;

    @Id
    private UUID id;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (!(object instanceof IdentifiableEntity)) return false;
        IdentifiableEntity other = (IdentifiableEntity) object;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
