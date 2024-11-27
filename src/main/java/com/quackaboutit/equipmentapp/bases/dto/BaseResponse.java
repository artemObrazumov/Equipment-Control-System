package com.quackaboutit.equipmentapp.bases.dto;

import com.quackaboutit.equipmentapp.unit.entity.Unit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private Long id;
    private Unit unit;
    private String address;
    private Double latitude;
    private Double longitude;

}
