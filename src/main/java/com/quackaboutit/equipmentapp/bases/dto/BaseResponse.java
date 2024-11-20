package com.quackaboutit.equipmentapp.bases.dto;

import com.quackaboutit.equipmentapp.unit.entity.Unit;
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
    private Long id;

    private Unit unit;

    private String address;

    private Double latitude;

    private Double longitude;

    public static BaseResponse fromBaseToResponse(Base base){
        return new BaseResponse(base.getId(), base.getUnit(), base.getAddress(), base.getLatitude(), base.getLongitude());
    }
}
