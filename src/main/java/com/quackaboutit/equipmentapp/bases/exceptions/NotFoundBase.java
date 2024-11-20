package com.quackaboutit.equipmentapp.bases.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundBase extends RuntimeException {
    public NotFoundBase(String message){
        super(message);
    }
}
