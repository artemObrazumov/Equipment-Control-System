package com.quackaboutit.equipmentapp.tracks.exceptions;

public class TrackIsClosed extends RuntimeException{
    public TrackIsClosed(){
        super("Track has been closed.");
    }
}
