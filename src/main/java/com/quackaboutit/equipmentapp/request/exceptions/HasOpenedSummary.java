package com.quackaboutit.equipmentapp.request.exceptions;

public class HasOpenedSummary extends RuntimeException {
    public HasOpenedSummary(){
        super("You have opened summary");
    }
}
