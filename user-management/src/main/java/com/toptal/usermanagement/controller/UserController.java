package com.toptal.usermanagement.controller;

import static org.springframework.http.HttpHeaders.LOCATION;

import java.time.Duration;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.toptal.usermanagement.assembler.UserResourceAssembler;
import com.toptal.usermanagement.dto.CreateUserResource;
import com.toptal.usermanagement.dto.UserResource;
import com.toptal.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserController {

    private static final String PATH_VARIABLE_USER_ID = "userId";

    private static final String PATH_USER_ID = "{" + PATH_VARIABLE_USER_ID + "}";

    private final UserService userService;
    private final UserResourceAssembler userResourceAssembler;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public Mono<ResponseEntity<UserResource>> createUser(@RequestBody Mono<CreateUserResource> userResource) {

        return userService.create(userResource.map(userResourceAssembler::toModel))
                .map(user -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.set(LOCATION, user.getId());
                    return ResponseEntity.ok()
                            .headers(headers)
                            .body(userResourceAssembler.toResource(user));
                });
    }

    @GetMapping("/users/" + PATH_USER_ID)
    public Mono<ResponseEntity<UserResource>> getBookById(
            @PathVariable(PATH_VARIABLE_USER_ID) String userId) {
        return userService
                .findById(userId)
                .map(userResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/hello", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> hello() {
        return Flux.fromIterable(List.of("Hello", "World", "!")).delayElements(Duration.ofSeconds(1));
    }




}
