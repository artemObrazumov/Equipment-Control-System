package com.quackaboutit.equipmentapp.equipment.dto;

import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.equipment.entity.EquipmentType;
import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;

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
public class NamedEquipmentResponse {
    @NotNull
    private Long id;

    @NotBlank
    @NotNull
    private String licensePlate;

    @NotBlank
    @NotNull
    private String carBrand;

    @NotNull
    private Base base;

    @NotNull
    private EquipmentType equipmentType;

    public static NamedEquipmentResponse fromNamedEquipmentToResponse(NamedEquipment equipment){
        return new NamedEquipmentResponse(equipment.getId(), equipment.getLicensePlate(),
        equipment.getCarBrand(), equipment.getBase(), equipment.getEquipmentType());
    }
}
