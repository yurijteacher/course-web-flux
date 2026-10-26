package ua.com.kneu.coursewebflux.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ua.com.kneu.coursewebflux.entity.Users;

@Repository
public interface UserRepository extends ReactiveCrudRepository<Users, Long> {

    @Query("SELECT * FROM users WHERE id=:id")
    Mono<Users> findById(Long id);

    @Query("SELECT id, username, password FROM users WHERE username = :username")
    Mono<Users> findByUsername(String username);

}

