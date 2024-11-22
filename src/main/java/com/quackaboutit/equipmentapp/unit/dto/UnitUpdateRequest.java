package com.quackaboutit.equipmentapp.unit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UnitUpdateRequest {
    @NotBlank
    @NotNull
    private String address;

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
