package com.quackaboutit.equipmentapp.request.dto;

import java.util.List;
import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.SummaryState;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.users.response.UserSummaryResponse;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryIdResponse {
    @NotNull
    Long id;

    @NotNull
    private SummaryState state;
    
    @NotNull
    private UserSummaryResponse manager;

    @NotNull
    private String date;

    @NotNull
    private Unit unit;

    @NotNull
    private List<Request> requests;
}
