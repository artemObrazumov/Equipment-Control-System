package com.quackaboutit.equipmentapp.tracks.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArrivalPointRequest {
    private Long id;
    private String driver;
    private LocalDateTime realArrivalTime;
    private LocalDateTime realOutTime;
    private Double kmOnStart;
    private Double kmOnEnd;
    private Double fuelOnStart;
    private Double fuelOnEnd;
    private LocalDateTime waitTime;
    private Double price;
}
