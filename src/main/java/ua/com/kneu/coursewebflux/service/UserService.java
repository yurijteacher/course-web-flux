package ua.com.kneu.coursewebflux.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.com.kneu.coursewebflux.entity.RolesHasUsers;
import ua.com.kneu.coursewebflux.entity.Users;
import ua.com.kneu.coursewebflux.repository.RolesHasUsersRepository;
import ua.com.kneu.coursewebflux.repository.RolesRepository;
import ua.com.kneu.coursewebflux.repository.UserRepository;

import java.util.HashSet;

@Service
public class UserService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final RolesRepository roleRepository;
    private final RolesHasUsersRepository rolesHasUsersRepository;

    public UserService(UserRepository userRepository, RolesRepository roleRepository,
                       RolesHasUsersRepository rolesHasUsersRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.rolesHasUsersRepository = rolesHasUsersRepository;
    }

    public Flux<GrantedAuthority> getAuthorities(Long userId) {
        return
                roleRepository.findByUserId(userId)
                        .cast(GrantedAuthority.class);
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        return userRepository.findByUsername(username)
                .flatMap(user ->
                        getAuthorities(user.getId())
                                .collectList()
                                .map(authorities -> {
                                    user.setAuthorities(new HashSet<>(authorities));
                                    return user;
                                })
                );
    }

    public Flux<Users> findAll() {
        return userRepository.findAll();
    }

    public Mono<UserDetails> registerUsers(Users user) {

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userRepository.save(user)
                .flatMap(savedUser -> {
                    RolesHasUsers rhu = new RolesHasUsers(savedUser.getId(), 1L);
                    return rolesHasUsersRepository.save(rhu)
                            .thenReturn(savedUser);
                });
    }
}
