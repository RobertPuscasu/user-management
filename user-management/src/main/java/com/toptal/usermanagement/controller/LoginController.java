package com.toptal.usermanagement.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toptal.usermanagement.dto.AuthRequest;
import com.toptal.usermanagement.dto.UserResource;
import com.toptal.usermanagement.service.UserService;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/authorize")
public class LoginController {

    private final UserService userService;

    @PostMapping
    public Mono<String> login(@Valid @RequestBody AuthRequest request) {
        return userService.login(request);
    }
}