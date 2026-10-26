package ua.com.kneu.coursewebflux.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "roles_has_users")
public class RolesHasUsers {

    @Id
    private Long id;
    private Long user_id;
    private Long role_id;

    public RolesHasUsers(Long user_id, Long role_id) {
        this.user_id = user_id;
        this.role_id = role_id;

    }
}