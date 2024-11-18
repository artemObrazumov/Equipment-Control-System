package com.quackaboutit.equipmentapp.request.dto;

import java.time.LocalDateTime;

import com.quackaboutit.equipmentapp.equipment.entity.Equipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EquipmentInRequest {
    @NotBlank
    @NotNull
    private Integer equipmentId;

    @NotBlank
    @NotNull
    private LocalDateTime arrivalTime;

    @NotBlank
    @NotNull
    private Integer quantity;
}
