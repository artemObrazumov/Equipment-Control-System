package com.quackaboutit.equipmentapp.tracks.dto;

import java.util.List;

import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackResponse {
    private Long id;
    private String date;
    private NamedEquipment namedEquipment;
    private String driver;
    private Boolean isActive;
    private List<ArrivalPointResponse> arrivalPoints;
}
