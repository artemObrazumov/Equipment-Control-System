package com.quackaboutit.equipmentapp.request.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestForRequest {
    @NotBlank
    @NotNull
    private Long workerId;

    @NotBlank
    @NotNull
    private Long unitId;

    @NotBlank
    @NotNull
    private Long workplaceId;

    @NotBlank
    @NotNull
    private Double distance;

    @NotBlank
    @NotNull
    private LocalDateTime arrivalDate;
    
    @NotBlank
    @NotNull
    private List<EquipmentInRequest> equipmentInRequest;
}
