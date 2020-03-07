package com.toptal.usermanagement.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.toptal.usermanagement.dto.AuthRequest;
import com.toptal.usermanagement.jwt.JwtAuthenticationManager;
import com.toptal.usermanagement.jwt.TokenProvider;
import com.toptal.usermanagement.model.Role;
import com.toptal.usermanagement.model.User;
import com.toptal.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationManager jwtAuthenticationManager;

    public Mono<String> login(AuthRequest request) {
        return Optional.ofNullable(request)
                .filter(Objects::nonNull)
                .map(req -> new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()))
                .map(authenticationToken -> {
                    ReactiveSecurityContextHolder.withAuthentication(authenticationToken);
                    return authenticationToken;
                })
                .map(authentication -> jwtAuthenticationManager.authenticate(authentication).map(tokenProvider::createToken))
                .orElse(Mono.empty());
    }

    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public Mono<User> create(Mono<User> user) {
        return user.map(u -> {
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            u.setRoles(List.of(Role.USER));
            return u;
        }).flatMap(userRepository::save);
    }


    public Mono<User> findById(String id) {
        return userRepository.findById(id);
    }
}
