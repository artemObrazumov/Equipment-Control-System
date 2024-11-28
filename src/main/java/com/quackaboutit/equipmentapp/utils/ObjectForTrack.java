package com.quackaboutit.equipmentapp.utils;

import java.time.LocalDateTime;

import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ObjectForTrack {
    private LocalDateTime timeArrive;
    private LocalDateTime timeOut;
    private Request request;
    private RequestedEquipment requestedEquipment;
}
