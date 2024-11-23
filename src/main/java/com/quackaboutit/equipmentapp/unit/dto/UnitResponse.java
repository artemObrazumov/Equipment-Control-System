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
    @NotNull
    private Long id;

    @NotBlank
    @NotNull
    private String address;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    public static UnitResponse fromUnit(Unit unit) {
        return new UnitResponse(unit.getId(),
                unit.getAddress(), unit.getLatitude(), unit.getLongitude());
    }
}
