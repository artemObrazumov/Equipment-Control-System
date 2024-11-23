package com.quackaboutit.equipmentapp.equipment.dto;

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
public class EquipmentResponse {
    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String image;

    public static EquipmentResponse fromEquipmentToResponse(Equipment equipment){
        return new EquipmentResponse(equipment.getName(), equipment.getImage());
    }
}
