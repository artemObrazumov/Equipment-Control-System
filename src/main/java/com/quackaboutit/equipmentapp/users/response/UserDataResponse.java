package com.quackaboutit.equipmentapp.users.response;

import com.quackaboutit.equipmentapp.unit.dto.UnitResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDataResponse {

    private Long id;
    private String name;
    private UnitResponse unit;
}
