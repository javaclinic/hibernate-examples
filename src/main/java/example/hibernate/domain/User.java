package example.hibernate.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

@Entity
@Table(name="hibernate_optimistic_locking_user")
@OptimisticLocking(type=OptimisticLockType.VERSION)
public class User implements Serializable {

    private static final long serialVersionUID = 403608754698094564L;

    // PROPERTIES
    @Id
    private UUID id;

    private String name;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private boolean active;

    @Version
    private Integer version;

    // CONSTRUCTORS
    public User() {
    }

    public User(UUID id, String name, String email, Date created, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.created = created;
        this.active = active;
    }

    // SETTERS AND GETTERS
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
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
    public Integer getVersion() {
        return version;
    }

    // OVERRIDE METHODS
    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, email=%s, created=%s, active=%s, version=%s]", id, name, email, created, active, version);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (!(object instanceof User)) return false;
        User other = (User) object;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (version == null) {
            if (other.version != null) return false;
        } else if (!version.equals(other.version)) {
            return false;
        }
        return true;
    }



}
