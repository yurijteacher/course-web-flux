package ua.com.kneu.coursewebflux.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.kneu.coursewebflux.entity.Roles;

@Repository
public interface RolesRepository extends ReactiveCrudRepository<Roles, Long> {

    @Query("SELECT * FROM roles WHERE id =:id")
    Mono<Roles> findById(Long id);


    @Query("SELECT rs.id, rs.name "+
            "FROM roles rs "+
            "JOIN roles_has_users rhu ON rs.id = rhu.role_id "+
            "JOIN users u ON rhu.user_id = u.id "+
            "WHERE u.id =:idd")
    Flux<Roles> findByUserId(Long idd);
    
}

