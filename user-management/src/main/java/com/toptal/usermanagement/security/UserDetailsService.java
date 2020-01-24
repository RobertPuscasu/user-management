package com.toptal.usermanagement.security;

import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.toptal.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsService implements ReactiveUserDetailsService, ReactiveUserDetailsPasswordService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String email) {
        return userRepository.findOneByEmail(email).map(user ->
        {
            System.out.println(user);
            return user;
        }).map(ReactiveUserDetails::new);
    }

    @Override
    public Mono<UserDetails> updatePassword(UserDetails userDetails, String newPassword) {
        return userRepository.findOneByEmail(userDetails.getUsername())
                .doOnSuccess(user -> user.setPassword(newPassword))
                .flatMap(userRepository::save)
                .map(ReactiveUserDetails::new);
    }
}
