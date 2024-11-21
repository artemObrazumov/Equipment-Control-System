package com.quackaboutit.equipmentapp.bases.dto;

import com.quackaboutit.equipmentapp.unit.entity.Unit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.quackaboutit.equipmentapp.bases.entity.Base;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class BaseResponse {

    @NotNull
    private Long id;

    @NotNull
    private Unit unit;

    @NotBlank
    @NotNull
    private String address;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    public static BaseResponse fromBaseToResponse(Base base){
        return new BaseResponse(base.getId(), base.getUnit(), 
        base.getAddress(), base.getLatitude(), base.getLongitude());
    }
}
