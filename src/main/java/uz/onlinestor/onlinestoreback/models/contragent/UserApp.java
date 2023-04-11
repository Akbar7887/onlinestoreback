package uz.onlinestor.onlinestoreback.models.contragent;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import uz.onlinestor.onlinestoreback.models.Status;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "userapp", uniqueConstraints = {
        @UniqueConstraint(columnNames = "phone"),
        @UniqueConstraint(columnNames = "username")
})
public class UserApp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String password;
    private String phone;
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private Date dateCreate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public UserApp() {
    }

    public UserApp(Long id, String username, String password, String phone, String email, Date dateCreate, Status active, List<Role> roles, Status status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.dateCreate = dateCreate;
        this.roles = roles;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

