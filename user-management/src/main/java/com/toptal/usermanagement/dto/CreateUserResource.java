package com.toptal.usermanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResource extends UserResource {
    private String password;
}
