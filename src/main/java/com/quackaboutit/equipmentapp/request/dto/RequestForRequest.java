package com.quackaboutit.equipmentapp.request.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestForRequest {
    @NotNull
    private Long unitId;

    @NotNull
    private Long workplaceId;

    @NotNull
    private Double distance;

    @NotNull
    private LocalDateTime arrivalDate;

    @NotNull
    private List<EquipmentInRequest> equipmentInRequest;
}
