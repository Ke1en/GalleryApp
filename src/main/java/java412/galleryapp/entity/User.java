package java412.galleryapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@EnableJpaAuditing
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @Column
    private UUID id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

}
