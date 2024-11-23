package com.quackaboutit.equipmentapp.workplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
public class WorkplaceRequest {
    
    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotBlank
    @NotNull
    private String address;
}
