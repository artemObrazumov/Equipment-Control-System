package com.quackaboutit.equipmentapp.equipment.dto;

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
public class NamedEquipmentRequest {
    @NotBlank
    @NotNull
    private String licensePlate;

    @NotNull
    private Long baseId;

    @NotNull
    private Long equipmentTypeId;
}
