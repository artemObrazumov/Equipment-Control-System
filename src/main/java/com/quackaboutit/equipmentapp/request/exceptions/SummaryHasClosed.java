package com.quackaboutit.equipmentapp.request.exceptions;

public class SummaryHasClosed extends RuntimeException {
    public SummaryHasClosed(){
        super("Summary has closed.");
    }
}
