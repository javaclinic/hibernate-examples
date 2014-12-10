package example.hibernate.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User entity class that defines few Hibernate NamedQuery and NamedNativeQuery queries.
 * 
 * @author nevenc
 *
 */
@Entity
@Table(name="hibernate_inheritence_user")
public class User extends IdentifiableEntity {

    private static final long serialVersionUID = -5666501110505240681L;

    private String name;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private boolean active;

    // CONSTRUCTORS
    public User() {
    }

    public User(UUID id, String name, String email, Date created, boolean active) {
        super.setId(id);
        this.name = name;
        this.email = email;
        this.created = created;
        this.active = active;
    }

    // SETTERS AND GETTERS
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    // OVERRIDE METHODS
    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, email=%s, created=%s, active=%s]", this.getId(), name, email, created, active);
    }

}
