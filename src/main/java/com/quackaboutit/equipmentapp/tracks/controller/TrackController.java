package com.quackaboutit.equipmentapp.tracks.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.tracks.dto.ArrivalPointResponse;
import com.quackaboutit.equipmentapp.tracks.service.ArrivalPointService;
import com.quackaboutit.equipmentapp.tracks.service.TrackService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/track")
@AllArgsConstructor
public class TrackController {
    private final TrackService trackService;
    private final ArrivalPointService arrivalPointService;

    @GetMapping("/track/equipment_tracks/{id}")
    private List<ArrivalPointResponse> getAllArrivalPointsByTimeArrival(@PathParam("id") Long id, @RequestParam Long timestamp){
        return trackService.getAllArrivalPointsByTimeArrival(id, timestamp);
    }
}
