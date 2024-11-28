package com.quackaboutit.equipmentapp.tracks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.tracks.dto.ArrivalPointResponse;
import com.quackaboutit.equipmentapp.tracks.repository.ArrivalPointRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ArrivalPointService {
    private final ArrivalPointRepository arrivalPointRepository;

    
}
