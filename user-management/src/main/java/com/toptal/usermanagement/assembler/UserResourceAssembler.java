package com.toptal.usermanagement.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.toptal.usermanagement.dto.UserResource;
import com.toptal.usermanagement.model.User;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserResourceAssembler {

    private final ModelMapper modelMapper;

    public UserResource toResource(User user) {
        return modelMapper.map(user, UserResource.class);
    }

    public User toModel(UserResource userResource) {
        return modelMapper.map(userResource, User.class);
    }
}
