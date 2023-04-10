package uz.onlinestor.onlinestoreback.models.contragent;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import uz.onlinestor.onlinestoreback.models.ACTIVE;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "userapp")
public class UserApp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private Date dateCreate;

    @Enumerated(value = EnumType.STRING)
    private ACTIVE active = ACTIVE.ACTIVE;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST})
    private Collection<Role> roles = new ArrayList<>();


    public UserApp() {
    }

    public UserApp(Long id, String username, String password, String phone, Date dateCreate, ACTIVE active, Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.dateCreate = dateCreate;
        this.active = active;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public ACTIVE getActive() {
        return active;
    }

    public void setActive(ACTIVE active) {
        this.active = active;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}

