package com.quackaboutit.equipmentapp.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserWithNameExists extends RuntimeException {
    public UserWithNameExists() {
        super("User with this username exists");
    }
}
