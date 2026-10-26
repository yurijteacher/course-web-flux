package ua.com.kneu.coursewebflux.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.kneu.coursewebflux.entity.Users;
import ua.com.kneu.coursewebflux.service.UserService;

@Component
public class GreetingHandler {

    private final UserService userService;

    public GreetingHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> hello(ServerRequest request) {

        return ServerResponse
                             .ok()
                             .contentType(MediaType.APPLICATION_JSON)
                             .bodyValue("Hello, Student!");
    }

    public Mono<ServerResponse> users(ServerRequest request) {

        Flux<Users> flux = userService.findAll();

//                Flux.just(
//                new Users(1L, "Vasya", "1234"),
//                new Users(2L, "Iva", "1111"),
//                new Users(3L, "Ivanka", "2222")
//        );

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flux, Users.class);
    }


}
