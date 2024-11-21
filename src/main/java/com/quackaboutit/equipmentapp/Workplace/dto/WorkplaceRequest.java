package com.quackaboutit.equipmentapp.workplace.dto;

import com.quackaboutit.equipmentapp.workplace.entity.WorkplaceState;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class WorkplaceRequest {
    @NotNull
    private Long Id;
    
    @NotNull
    private WorkplaceState state;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotBlank
    @NotNull
    private String address;
}
