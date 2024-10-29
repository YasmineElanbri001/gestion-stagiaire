package emsi.ma.stagiaire.config.model;

import emsi.ma.stagiaire.enumeration.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.FileStore;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Vueillez saisir le nom d'utilisateur.")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Vueillez saisir le mot de passe.")
    @Size(min = 8, message = "8 caractéres au minimum.")
    private String password;

    @NotBlank(message = "Vueillez confirmer le mot de passe")
    @Transient
    private String repassword;

    @NotNull(message = "Vueillez séléctionner le rôle.")
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean active;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getRepassword() {
        return repassword;
    }

    public Role getRole() {
        return role;
    }

    public Long getId() { return id; }

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
        return active;
    }

}
