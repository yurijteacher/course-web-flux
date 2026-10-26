package ua.com.kneu.coursewebflux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;
import ua.com.kneu.coursewebflux.entity.Category;
import ua.com.kneu.coursewebflux.service.CategoryService;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public String getCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "category";
    }

    @PostMapping("/updateCategory")
    public Mono<String> updateCategory(@ModelAttribute("category") Category category) {
        return categoryService.updateCategory(category).thenReturn("redirect:/category");
    }
    @PostMapping("/deleteCategory")
    public Mono<String> deleteCategory(@ModelAttribute Category category) {
        return categoryService.deleteById(category.getId()).thenReturn("redirect:/category");
    }
    @GetMapping("/new-category")
    public String getNewCategory(Model model) {
        model.addAttribute("category", new Category());
        return "form-category";
    }
    @PostMapping("/saveNewCategory")
    public Mono<String> saveNewCategory(@ModelAttribute("category") Category category) {
        return categoryService.save(category).thenReturn("redirect:/category");
    }
}
