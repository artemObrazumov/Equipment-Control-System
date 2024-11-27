package com.quackaboutit.equipmentapp.workplace.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkplaceItemResponse {
    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    private Integer requestsProcessed;

    @NotNull
    private Integer requestsSent;
}
