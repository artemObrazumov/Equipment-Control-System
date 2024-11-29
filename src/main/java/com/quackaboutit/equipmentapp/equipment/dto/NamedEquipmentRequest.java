package com.quackaboutit.equipmentapp.equipment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NamedEquipmentRequest {
    @NotBlank
    @NotNull
    private String licensePlate;

    @NotBlank
    @NotNull
    private String carBrand;

    @NotNull
    private Long baseId;

    @NotNull
    private Long equipmentTypeId;

    @NotNull
    @NotBlank
    private String fuelType;

    @NotNull
    private Integer condition;

    @NotNull
    private Integer paymentHourly;
}
