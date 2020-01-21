package com.toptal.usermanagement.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.toptal.usermanagement.model.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findOneByEmail(String email);
}
