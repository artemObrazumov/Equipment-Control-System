package com.quackaboutit.equipmentapp.tracks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArrivalPointResponse {
    private String address;
    private String planOutTime;
    private String planArrivalTime;
}
