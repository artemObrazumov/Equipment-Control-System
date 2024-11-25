package com.quackaboutit.equipmentapp.equipment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@Builder
public class EquipmentTypeDetailsResponse {
    @NotNull
    private Long id;

    @NotBlank
    @NotNull
    private String type;

    private String equipmentName;
    private EquipmentTypeAnalytics analytics;
}
