package com.quackaboutit.equipmentapp.tracks.dto;

import java.time.Duration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArrivalPointResponse {
    private Long id;
    private String address;
    private String planOutTime;
    private String planArrivalTime;
    private Double distanse;
    private Duration planWorkDuration;
    private String realArrivalTime;
    private String realOutTime;
    private Double kmOnStart;
    private Double kmOnEnd;
    private Double fuelOnStart;
    private Double fuelOnEnd;
    private String waitTime;
    private Double baseLatitude;
    private Double baseLongitude;
}
