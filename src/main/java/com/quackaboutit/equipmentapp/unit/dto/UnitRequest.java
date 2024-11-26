package com.quackaboutit.equipmentapp.unit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnitRequest {

    @NotBlank
    @NotNull
    private String address;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
