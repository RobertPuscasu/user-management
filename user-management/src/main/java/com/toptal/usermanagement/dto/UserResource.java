package com.toptal.usermanagement.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class UserResource {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
}
