package com.quackaboutit.equipmentapp.equipment.dto;

import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.equipment.entity.EquipmentType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NamedEquipmentResponse {
    private Long id;
    private String licensePlate;
    private String carBrand;
    private Base base;
    private EquipmentType equipmentType;
    private Boolean isActive;
    private String lastWorkPlaceAddress;
    private String finishTime;
}
