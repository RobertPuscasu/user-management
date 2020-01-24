package com.toptal.usermanagement.jwt;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final ReactiveUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        return Optional.of(authentication)
                .filter(Authentication::isAuthenticated)
                .map(Mono::just)
                .orElseGet(() -> Mono.just(authentication)
                            .switchIfEmpty(Mono.defer(this::raiseBadCredentials))
                            .cast(UsernamePasswordAuthenticationToken.class)
                            .flatMap(this::authenticateToken)
                            .publishOn(Schedulers.parallel())
                            .onErrorResume(e -> raiseBadCredentials())
                            .filter(u -> passwordEncoder.matches((String) authentication.getCredentials(), u.getPassword()))
                            .switchIfEmpty(Mono.defer(this::raiseBadCredentials))
                            .map(u -> new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), u.getAuthorities()))
                );
    }

    private <T> Mono<T> raiseBadCredentials() {
        return Mono.error(new BadCredentialsException("Invalid Credentials"));
    }

    private Mono<UserDetails> authenticateToken(final UsernamePasswordAuthenticationToken authenticationToken) {
        return Optional.of(authenticationToken)
                .map(UsernamePasswordAuthenticationToken::getName)
                .filter(Objects::nonNull)
                .filter($ -> Objects.isNull(SecurityContextHolder.getContext().getAuthentication()))
                .map(userDetailsService::findByUsername)
                .orElse(Mono.empty());
    }
}
