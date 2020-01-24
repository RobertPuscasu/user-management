package com.toptal.usermanagement.handler;


import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.toptal.usermanagement.common.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ApiErrorResponse> handleUnauthorizedException(ServerHttpRequest request,
                                                              RuntimeException e) {
        final var apiErrorResponse = new ApiErrorResponse(
                request.getPath().value(),
                UNAUTHORIZED,
                "Unauthorized!");
        return createErrorResponseInfo(apiErrorResponse, e);
    }

    private ResponseEntity<ApiErrorResponse> createErrorResponseInfo(ApiErrorResponse apiErrorResponse,
                                                                     Exception e) {
        final var status = apiErrorResponse.getStatus();
        final var path = apiErrorResponse.getPath();
        final var message = apiErrorResponse.getMessage();
        if (apiErrorResponse.getStatus().isError()) {
            log.error("Returning HTTP status: {} for path: {}, message: {}",
                    status, path, message, e);
        } else {
            log.info("Returning HTTP status: {} for path: {}, message: {}",
                    status, path, message, e);
        }
        return new ResponseEntity<>(apiErrorResponse, apiErrorResponse.getStatus());
    }
}
