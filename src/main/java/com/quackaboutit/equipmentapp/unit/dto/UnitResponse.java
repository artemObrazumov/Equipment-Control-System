package com.quackaboutit.equipmentapp.unit.dto;

import com.quackaboutit.equipmentapp.unit.entity.Unit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UnitResponse {

    @NotBlank
    @NotNull
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String address;

    @NotBlank
    @NotNull
    private Double latitude;

    @NotBlank
    @NotNull
    private Double longitude;

    public static UnitResponse fromUnit(Unit unit) {
        return new UnitResponse(unit.getId(), unit.getName(),
                unit.getAddress(), unit.getLatitude(), unit.getLongitude());
    }
}
