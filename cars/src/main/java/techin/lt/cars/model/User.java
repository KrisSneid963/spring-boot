package techin.lt.cars.model;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rental")
    @JoinColumn(name = "user_id")

    private List<Rental> rental;

    @ManyToMany(fetch = FetchType.EAGER)
//    cascade = CascadeType.PERSIST)
    //we use it when we want to set the role registering role
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")

    )
    private List<Role> roles;

    public User() {
    }

    public User(long id, String username, String password, List<Rental> rental, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rental = rental;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Rental> getRentals() {
        return rental;
    }

    public void setRentals(List<Rental> rentals) {
        this.rental = rental;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> (GrantedAuthority) role::getName)
                .collect(Collectors.toList());
    }

}
