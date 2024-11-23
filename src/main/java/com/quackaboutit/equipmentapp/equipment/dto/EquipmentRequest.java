package com.quackaboutit.equipmentapp.equipment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class EquipmentRequest {
    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String image;
}
