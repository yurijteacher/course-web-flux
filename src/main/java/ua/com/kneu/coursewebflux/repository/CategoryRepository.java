package ua.com.kneu.coursewebflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ua.com.kneu.coursewebflux.entity.Category;

@Repository
public interface CategoryRepository extends ReactiveCrudRepository<Category, Long> {

    Mono<Category> findByName(String name);

}
