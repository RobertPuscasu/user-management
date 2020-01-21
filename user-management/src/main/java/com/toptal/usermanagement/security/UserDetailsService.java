package com.toptal.usermanagement.security;

import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.toptal.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsService implements ReactiveUserDetailsService, ReactiveUserDetailsPasswordService {

    private final UserService userService;

    @Override
    public Mono<UserDetails> findByUsername(String email) {
        return userService.findOneByEmail(email).map(user ->
        {
            System.out.println(user);
            return user;
        }).map(ReactiveUserDetails::new);
    }

    @Override
    public Mono<UserDetails> updatePassword(UserDetails userDetails, String newPassword) {
        return userService.findOneByEmail(userDetails.getUsername())
                .doOnSuccess(user -> user.setPassword(newPassword))
                .flatMap(userService::update)
                .map(ReactiveUserDetails::new);
    }
}
