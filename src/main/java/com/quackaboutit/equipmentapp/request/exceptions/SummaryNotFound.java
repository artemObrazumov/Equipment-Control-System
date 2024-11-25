package com.quackaboutit.equipmentapp.request.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SummaryNotFound extends RuntimeException {
    public SummaryNotFound(){
        super("Summary doesn`t exist.");
    }
}
