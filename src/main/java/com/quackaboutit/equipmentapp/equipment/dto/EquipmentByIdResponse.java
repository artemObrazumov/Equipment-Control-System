package com.quackaboutit.equipmentapp.equipment.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentByIdResponse {
    private Long id;
    private String name;
    private String image;
    private List<EquipmentTypeResponse> equipmentTypeResponses;

}
