package com.toptal.usermanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.toptal.usermanagement.model.Role;
import com.toptal.usermanagement.model.User;
import com.toptal.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public Mono<User> findOneByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public Mono<User> create(Mono<User> user) {
        return user.map(u -> {
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            u.setRoles(List.of(Role.USER));
            return u;
        }).flatMap(userRepository::save);
    }

    public Mono<User> update(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public Mono<Void> deleteById(String id) {
        return userRepository.deleteById(id);
    }
}
