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
@AllArgsConstructor
public class EquipmentTypeResponse {

    private Long id;
    private String type;
    private Integer count;

    public static EquipmentTypeResponse fromEquipmentTypeToResponse(EquipmentType equipmentType){
        return new EquipmentTypeResponse(equipmentType.getId(),
                equipmentType.getType(), null);
    }

    public static EquipmentTypeResponse fromEquipmentTypeToResponse(EquipmentType equipmentType, Integer count){
        return new EquipmentTypeResponse(equipmentType.getId(),
        equipmentType.getType(), count);
    }
}
