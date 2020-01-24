package com.toptal.usermanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
    private String userName;
    private String password;
}