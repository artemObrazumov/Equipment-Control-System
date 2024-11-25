package com.quackaboutit.equipmentapp.request.dto;

import java.util.List;

import com.quackaboutit.equipmentapp.request.entity.Summary;
import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.SummaryState;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.users.response.UserSummaryResponse;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
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

    public static SummaryIdResponse fromSummaryToResponse(Summary summary){
        return new SummaryIdResponse(summary.getId(), summary.getState(),
        UserSummaryResponse.fromUserToSummaryResponse(summary.getManager()), summary.getDate().toString(), summary.getUnit(),
        summary.getRequests());
    }
}