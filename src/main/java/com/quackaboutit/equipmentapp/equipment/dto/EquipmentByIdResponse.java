package com.quackaboutit.equipmentapp.equipment.dto;

import java.util.List;

import com.quackaboutit.equipmentapp.equipment.entity.Equipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class EquipmentByIdResponse {
    @NotNull
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String image;

    @NotNull
    private List<EquipmentTypeResponse> equipmentTypeResponses;

    public static EquipmentByIdResponse fromEquipmentToResponse(Equipment equipment, List<EquipmentTypeResponse> equipmentTypeResponses){
        return new EquipmentByIdResponse(equipment.getId(), 
        equipment.getName(), equipment.getImage(), equipmentTypeResponses);
    }
}
