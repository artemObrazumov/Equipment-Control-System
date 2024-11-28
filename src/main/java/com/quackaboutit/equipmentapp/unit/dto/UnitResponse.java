package com.quackaboutit.equipmentapp.unit.dto;

import com.quackaboutit.equipmentapp.unit.entity.Unit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnitResponse {

    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer workplaces;
    private Integer requests;
}
