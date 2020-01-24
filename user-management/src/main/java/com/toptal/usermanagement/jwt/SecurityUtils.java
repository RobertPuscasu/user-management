package com.toptal.usermanagement.jwt;

import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public interface SecurityUtils {

     static String getTokenFromRequest(ServerWebExchange serverWebExchange) {
         return Optional.of(serverWebExchange)
                 .map(ServerWebExchange::getRequest)
                 .map(ServerHttpRequest::getHeaders)
                 .map(h -> h.getFirst(HttpHeaders.AUTHORIZATION))
                 .orElse(Strings.EMPTY);
    }

     static Mono<String> getUserFromRequest(ServerWebExchange serverWebExchange) {
        return serverWebExchange.getPrincipal()
                .cast(UsernamePasswordAuthenticationToken.class)
                .map(UsernamePasswordAuthenticationToken::getPrincipal)
                .cast(String.class);
    }
}
