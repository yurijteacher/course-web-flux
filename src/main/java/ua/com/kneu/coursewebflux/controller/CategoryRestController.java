package ua.com.kneu.coursewebflux.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.kneu.coursewebflux.entity.Category;
import ua.com.kneu.coursewebflux.service.CategoryService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryRestController {

        private final CategoryService categoryService;

        // Отримати всі категорії
        @GetMapping("/category")
        public Flux<Category> getCategories() {
            return categoryService.findAll();
        }

        // Отримати категорію за ID
        @GetMapping("/category/{id}")
        public Mono<ResponseEntity<Category>> getCategoryById(
                @PathVariable("id") Long id) {

            return categoryService.findById(id)
                    .map(category ->
                            ResponseEntity.ok(category))
                    .defaultIfEmpty(
                            ResponseEntity.notFound().build());
        }

        // Додати категорію
        @PostMapping("/category")
        public Mono<ResponseEntity<Category>> saveCategory(
                @RequestBody Category category) {

            return categoryService.save(category)
                    .map(savedCategory ->
                            ResponseEntity.status(HttpStatus.CREATED)
                                    .body(savedCategory));
        }

        // Оновити категорію
        @PutMapping("/category/{id}")
        public Mono<ResponseEntity<Category>> updateCategory(
                @PathVariable("id") Long id,
                @RequestBody Category category) {

            category.setId(id);

            return categoryService.findById(id)
                    .flatMap(existingCategory ->
                            categoryService.updateCategory(category))
                    .map(updatedCategory ->
                            ResponseEntity.ok(updatedCategory))
                    .defaultIfEmpty(
                            ResponseEntity.notFound().build());
        }

        // Видалити категорію за ID
        @DeleteMapping("/category/{id}")
        public Mono<ResponseEntity<Void>> deleteCategory(
                @PathVariable("id") Long id) {

            return categoryService.findById(id)
                    .flatMap(category ->
                            categoryService.deleteById(id)
                                    .thenReturn(
                                            ResponseEntity.noContent().<Void>build()))
                    .defaultIfEmpty(
                            ResponseEntity.notFound().build());
        }

        // Видалити всі категорії
        @DeleteMapping("/category")
        public Mono<ResponseEntity<Void>> deleteAllCategory() {

            return categoryService.deleteAll()
                    .thenReturn(
                            ResponseEntity
                                    .noContent()
                                    .<Void>build());
        }
    }