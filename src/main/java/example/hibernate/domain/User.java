package example.hibernate.domain;

import java.io.Serializable;
import java.util.Date;

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

    private static final long serialVersionUID = -5742670858090161566L;

    // PROPERTIES
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    private boolean active;

    // CONSTRUCTORS
    public User() {
    }

    public User(Integer id, String name, String email, Date created, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.created = created;
        this.active = active;
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

    // OVERRIDE METHODS
    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, email=%s, created=%s, active=%s]", id, name, email, created, active);
    }

}
