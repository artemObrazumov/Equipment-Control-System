package com.quackaboutit.equipmentapp.equipment.exceptions;

public class EquipmentNotFound extends RuntimeException {
    public EquipmentNotFound(){
        super("Equipment doesn`t exist");
    }
}
