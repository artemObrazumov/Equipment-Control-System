package com.quackaboutit.equipmentapp.request.dto;

import java.time.Duration;
import java.time.LocalDateTime;
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

    @NotNull
    private String licensePlateNumber;
}
