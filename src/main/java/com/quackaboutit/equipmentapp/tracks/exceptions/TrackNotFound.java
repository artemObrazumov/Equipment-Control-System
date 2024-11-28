package com.quackaboutit.equipmentapp.tracks.exceptions;

public class TrackNotFound extends RuntimeException {
    public TrackNotFound(){
        super("Track doesn`t exist");
    }
}
