package com.quackaboutit.equipmentapp.request.dto;

import com.quackaboutit.equipmentapp.request.entity.SummaryState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryResponse {
    @NotNull
    Long id;

    @NotNull
    private SummaryState state;

    @NotNull
    private String managerName;

    @NotNull
    private String date;
}
