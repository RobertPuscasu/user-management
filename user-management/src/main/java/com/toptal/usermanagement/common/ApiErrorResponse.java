package com.toptal.usermanagement.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
public final class ApiErrorResponse {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS", shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;
    private String path;
    private HttpStatus status;
    private String message;

    public ApiErrorResponse(String path, HttpStatus status, String message) {
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.status = status;
        this.message = message;
    }

}
