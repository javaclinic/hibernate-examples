package example.hibernate.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User entity class that defines few Hibernate NamedQuery and NamedNativeQuery queries.
 * 
 * @author nevenc
 *
 */
@Entity
@Table(name="hibernate_inheritence_category")
public class Category extends IdentifiableEntity {

    private static final long serialVersionUID = -1210727483451632958L;

    private String name;
    private String description;

    // CONSTRUCTORS
    public Category() {
    }

    public Category(UUID id, String name, String description) {
        super.setId(id);
        this.name = name;
        this.description = description;
    }

    // SETTERS AND GETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // OVERRIDE METHODS
    @Override
    public String toString() {
        return String.format("Category [id=%s, name=%s, description=%s]", this.getId(), name, description);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!super.equals(object)) return false;
        if (!(object instanceof Category)) return false;
        Category other = (Category) object;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
