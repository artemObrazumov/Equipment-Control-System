package com.quackaboutit.equipmentapp.equipment.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentBestOptionsResponse {

    private List<NamedEquipmentResponse> onBaseEquipment;
    private List<NamedEquipmentResponse> contractorEquipment;
}
