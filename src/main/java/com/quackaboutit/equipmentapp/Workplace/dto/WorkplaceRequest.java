package com.quackaboutit.equipmentapp.workplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class WorkplaceRequest {
    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotBlank
    @NotNull
    private String address;
}
