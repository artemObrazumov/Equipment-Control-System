package com.quackaboutit.equipmentapp.utils;

import com.quackaboutit.equipmentapp.tracks.entity.ArrivalPoint;
import com.quackaboutit.equipmentapp.tracks.entity.Track;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ObjectForWorkplaceReport {
    private Track track;
    private ArrivalPoint arrivalPoint;
    
}
