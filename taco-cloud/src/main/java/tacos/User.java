package tacos;

import java.util.Arrays;
import java.util.Collection;
import javax.persistence.*;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.
        SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
@AllArgsConstructor
@Table(name="users")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private  String password;
    @Column(name="fullname")
    private  String fullname;
    @Column(name="street")
    private  String street;
    @Column(name="city")
    private  String city;
    @Column(name="state")
    private  String state;
    @Column(name="zip")
    private  String zip;
    @Column(name="phoneNumber")
    private  String phoneNumber;


    public User(String username, String password, String fullname, String street, String city, String state, String zip, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER")
               // , new SimpleGrantedAuthority("ROLE_ADMIN")
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}