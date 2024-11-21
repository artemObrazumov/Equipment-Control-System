package com.quackaboutit.equipmentapp.unit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnitNotFound extends RuntimeException {
    public UnitNotFound(){
        super("Unit with this ID doesn`t exist");
    }
}
