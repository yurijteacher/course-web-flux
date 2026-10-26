package ua.com.kneu.coursewebflux.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;
import ua.com.kneu.coursewebflux.entity.Users;
import ua.com.kneu.coursewebflux.service.UserService;

@Controller
public class TestController {
    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getForm(Model model) {
        model.addAttribute("title", "From Authentication");
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("user", new Users());
        return "registration";
    }

    @PostMapping("/registration")
    public Mono<String> registration(@Valid @ModelAttribute("user") Users user,
                                     BindingResult result
    ) {
        if(result.hasErrors()) {
            return Mono.just("registration");
        }

        //

        return userService.registerUsers(user).thenReturn("redirect:/login");
    }
}
