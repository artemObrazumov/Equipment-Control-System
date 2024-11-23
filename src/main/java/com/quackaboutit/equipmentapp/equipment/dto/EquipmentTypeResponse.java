package com.quackaboutit.equipmentapp.equipment.dto;

import com.quackaboutit.equipmentapp.equipment.entity.Equipment;
import com.quackaboutit.equipmentapp.equipment.entity.EquipmentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class EquipmentTypeResponse {
    @NotNull
    private Long id;

    @NotBlank
    @NotNull
    private String type;

    public static EquipmentTypeResponse fromEquipmentTypeToResponse(EquipmentType equipmentType){
        return new EquipmentTypeResponse(equipmentType.getId(), 
        equipmentType.getType());
    }
}
