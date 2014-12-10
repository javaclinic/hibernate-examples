package example.hibernate.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="hibernate_simple_user")
public class User implements Serializable {

    // PROPERTIES
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Embedded
    private Address address;

    private boolean active;

    // CONSTRUCTORS
    public User() {
    }

    public User(Integer id, String name, String email, Date created, boolean active, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.created = created;
        this.active = active;
        this.address = address;
    }

    // SETTERS AND GETTERS
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    // OVERRIDE METHODS
    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, email=%s, created=%s, active=%s, %s]", id, name, email, created, active, address);
    }

    @Override
    public int hashCode() {
        return ((email == null) ? System.identityHashCode(this) : 31 * email.hashCode());
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (!(object instanceof User)) return false;
        User other = (User) object;
        if (email == null) {
            if (other.email != null) return false;
        } else if (!email.equals(other.email)) {
            return false;
        }
        return true;
    }
}
