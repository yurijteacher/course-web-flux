package ua.com.kneu.coursewebflux.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import ua.com.kneu.coursewebflux.service.UserService;


@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    private final UserService userService;

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//                NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {

        UserDetailsRepositoryReactiveAuthenticationManager authManager =
                new UserDetailsRepositoryReactiveAuthenticationManager(userService);
                authManager.setPasswordEncoder(passwordEncoder());

        return authManager;
    }


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

        return http
                .csrf(csrfSpec -> csrfSpec.disable())
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/", "/login", "/registration")
                                .permitAll()
                                .pathMatchers("/users",
                                        "/category","/updateCategory","/new-category","/saveNewCategory"
                                        )
                                        .hasRole("User")
                                .pathMatchers("/manager")
                                        .hasRole("Manager")
                                .pathMatchers("/admin").
                                        hasRole("Admin")
                                .pathMatchers("/top-admin")
                                        .hasRole("Top")
                                .anyExchange()
                                .authenticated())
                .formLogin(
                        formLogin ->
                                formLogin.loginPage("/login")
                                        .authenticationSuccessHandler(
                                                new RedirectServerAuthenticationSuccessHandler("/")
//                        withDefaults()
                                        )
                )
                .authenticationManager(authenticationManager())
                .build();
    }

}