package com.quackaboutit.equipmentapp.equipment.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestedEquipmentResponse {

    private Long id;
    private Long equipmentId;
    private String equipmentName;
    private String equipmentImage;
    private Long equipmentTypeId;
    private String equipmentType;
    private String licensePlateNumber;
    private String arrivalTime;
    private String workDuration;
    private List<EquipmentTypeResponse> types;
}
