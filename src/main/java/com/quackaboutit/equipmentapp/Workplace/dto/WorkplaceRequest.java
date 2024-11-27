package com.quackaboutit.equipmentapp.workplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkplaceRequest {
    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotBlank
    @NotNull
    private String address;

    @NotNull
    private Long unitId;
}
