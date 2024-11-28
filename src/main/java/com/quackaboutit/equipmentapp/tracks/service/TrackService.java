package com.quackaboutit.equipmentapp.tracks.service;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.quackaboutit.equipmentapp.tracks.entity.ArrivalPoint;
import com.quackaboutit.equipmentapp.tracks.entity.Track;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.tracks.dto.ArrivalPointResponse;
import com.quackaboutit.equipmentapp.tracks.dto.TrackResponse;
import com.quackaboutit.equipmentapp.tracks.exceptions.TrackNotFound;
import com.quackaboutit.equipmentapp.tracks.repository.TrackRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrackService {
    private final TrackRepository trackRepository;
    
    // public TrackResponse create(){
    //     return TrackResponse.builder().build(); // заглушка
    // }

    public List<ArrivalPointResponse> getAllArrivalPointsByTimeArrival(Long id, Long timestamp){
        Track track = trackRepository.findById(id).orElseThrow(
                            () -> new TrackNotFound());
        List<ArrivalPoint> arrivalPoints = track.getArrivalPoint();
        List<ArrivalPointResponse> arrivalPointResponses = new ArrayList<>();

        arrivalPoints.forEach(arrivalPoint -> {
            if(arrivalPoint.getPlanArrivalTime().
                toInstant(ZoneOffset.UTC).toEpochMilli() >= timestamp && timestamp <= 
                arrivalPoint.getPlanArrivalTime().
                toInstant(ZoneOffset.UTC).toEpochMilli() + 86400000){
                    arrivalPointResponses.add(ArrivalPointResponse.builder()
                                                .address(arrivalPoint.getAddress())
                                                .planArrivalTime(arrivalPoint.getPlanArrivalTime().toString())
                                                .planOutTime(arrivalPoint.getPlanOutTime().toString())
                                                .build());
                }
                
        });

        return arrivalPointResponses;
    }
    
}
