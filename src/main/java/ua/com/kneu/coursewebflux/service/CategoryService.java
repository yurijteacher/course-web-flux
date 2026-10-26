package ua.com.kneu.coursewebflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.kneu.coursewebflux.entity.Category;
import ua.com.kneu.coursewebflux.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Flux<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Mono<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Mono<Category> save(Category category) {
        return categoryRepository.save(category);
    }

    public Mono<Void> deleteById(Long id) {
        return categoryRepository.deleteById(id);
    }

    public Mono<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Mono<Category> updateCategory(Category category) {
        return categoryRepository.save(category);
    }

}