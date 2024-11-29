package com.quackaboutit.equipmentapp.tracks.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.tracks.dto.ArrivalPointRequest;
import com.quackaboutit.equipmentapp.tracks.dto.ArrivalPointResponse;
import com.quackaboutit.equipmentapp.tracks.dto.TrackResponse;
import com.quackaboutit.equipmentapp.tracks.service.ArrivalPointService;
import com.quackaboutit.equipmentapp.tracks.service.TrackService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/track")
@AllArgsConstructor
public class TrackController {
    private final TrackService trackService;
    private final ArrivalPointService arrivalPointService;

    @GetMapping("/equipment_tracks/{id}")
    private List<ArrivalPointResponse> getAllArrivalPointsByTimeArrival(@PathVariable Long id, @RequestParam Long timestamp){
        return trackService.getAllArrivalPointsByTimeArrival(id, timestamp);
    }

    @GetMapping("/{id}")
    private TrackResponse getTrackById(@PathVariable Long id){
        return trackService.getTrackById(id);
    }

    @PutMapping("/{id}/real_data")
    private void pushRealData(@PathVariable Long id, @Valid @RequestBody List<ArrivalPointRequest> requests){
        trackService.pushRealData(requests, id);
    }

    @GetMapping
    private List<TrackResponse> getAllTracks(){
        return trackService.getAllTracks();
    }
}
