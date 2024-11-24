package com.quackaboutit.equipmentapp.equipment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquipmentNotFound extends RuntimeException {
    public EquipmentNotFound(){
        super("Equipment doesn`t exist");
    }
}
