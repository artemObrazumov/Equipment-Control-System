package com.quackaboutit.equipmentapp.workplace.exceptions;

public class WorkplaceNotFound extends RuntimeException {
    public WorkplaceNotFound(){
        super("Workplace doesn`t exist");
    }
}