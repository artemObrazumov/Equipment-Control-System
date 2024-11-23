package com.quackaboutit.equipmentapp.utils;

import java.util.regex.Pattern;

public class LicensePlate {
    public static void checkLicensePlate(String licensePlate) throws LicensePlateNoPattern {
        if(Pattern.matches("^([А-ЯA-Z]{1}[0-9]{3}[А-ЯA-Z]{2}|[А-ЯA-Z]{2}[0-9]{3}[А-ЯA-Z]{1,2})\\d{2,3}$", licensePlate))
            return ;
        throw new LicensePlateNoPattern();
    }
}
