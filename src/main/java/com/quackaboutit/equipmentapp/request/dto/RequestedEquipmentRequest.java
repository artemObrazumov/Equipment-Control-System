package com.quackaboutit.equipmentapp.request.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class RequestedEquipmentRequest {

    @NotNull
    private Long equipmentId;

    @NotNull
    private Long equipmentTypeId;

    @NotNull
    @NotBlank
    private String licensePlateNumber;

    @NotNull
    private LocalDateTime arrivalTime;

    @NotNull
    private Duration workDuration;
}
