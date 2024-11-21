package com.quackaboutit.equipmentapp.unit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UnitRequest {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String address;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
