package com.quackaboutit.equipmentapp.workplace.dto;
import com.quackaboutit.equipmentapp.unit.dto.UnitResponse;
import com.quackaboutit.equipmentapp.workplace.entity.WorkplaceState;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkplaceResponse {
    @NotNull
    private Long id;

    @NotNull
    private WorkplaceState state;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    private UnitResponse unit;
}
