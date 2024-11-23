package com.quackaboutit.equipmentapp.utils;

public class LicensePlateNoPattern extends RuntimeException {
    public LicensePlateNoPattern(){
        super("The machine number does not match the pattern.");
    }
}
