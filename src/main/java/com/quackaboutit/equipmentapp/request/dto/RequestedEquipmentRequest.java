package com.quackaboutit.equipmentapp.request.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
