package com.quackaboutit.equipmentapp.equipment.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestedEquipmentResponse {

    private Long equipmentId;
    private String equipmentName;
    private String equipmentImage;
    private Long equipmentTypeId;
    private String equipmentType;
    private String licensePlateNumber;
    private String arrivalTime;
}
