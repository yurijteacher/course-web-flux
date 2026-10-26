package ua.com.kneu.coursewebflux.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "roles")
public class Roles implements GrantedAuthority {

    @Id
    private Long id;
    private String name;

    @Override
    public @Nullable String getAuthority() {
        return name;
    }
}