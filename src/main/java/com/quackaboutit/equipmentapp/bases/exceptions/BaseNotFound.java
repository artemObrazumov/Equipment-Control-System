package com.quackaboutit.equipmentapp.bases.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BaseNotFound extends RuntimeException {
    public BaseNotFound(){
        super("Base with this ID doesn`t exist");
    }
}
