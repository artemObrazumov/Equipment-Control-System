package com.quackaboutit.equipmentapp.request.dto;

import java.time.Duration;
import java.time.LocalDateTime;

import com.quackaboutit.equipmentapp.equipment.entity.Equipment;
import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentInRequest {
    @NotNull
    private Long equipmentId;

    @NotNull
    private Long equipmentTypeId;

    @NotNull
    private LocalDateTime arrivalTime;

    @NotNull
    private Integer quantity;

    @NotNull
    private Duration workDuration;
}
