package com.quackaboutit.equipmentapp.equipment.dto;

import lombok.*;

@Data
@Getter
@Builder
@AllArgsConstructor
public class EquipmentTypeResponse {
    private Long id;
    private String type;
    private Integer count;
}
