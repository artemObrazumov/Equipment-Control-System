package com.quackaboutit.equipmentapp.request.dto;

import java.time.LocalDateTime;

import com.quackaboutit.equipmentapp.equipment.entity.Equipment;
import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EquipmentInRequest {
    @NotBlank
    @NotNull
    private Long equipmentId;

    @NotBlank
    @NotNull
    private LocalDateTime arrivalTime;

    @NotBlank
    @NotNull
    private Integer quantity;

    static public RequestedEquipment fromEquipmentInRequest(EquipmentInRequest eir, 
    Equipment re){
        return new RequestedEquipment(null, re, 
        eir.getArrivalTime(), eir.getQuantity());
    }
}
