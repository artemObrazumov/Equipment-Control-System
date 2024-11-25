package com.quackaboutit.equipmentapp.request.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.Summary;
import com.quackaboutit.equipmentapp.request.entity.SummaryState;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.users.entity.User;
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
public class SummaryResponse {
    @NotNull
    Long id;

    @NotNull
    private SummaryState state;

    @NotNull
    private String managerName;

    @NotNull
    private String date;
    
    public static SummaryResponse fromSummaryToResponse(Summary summary){
        return new SummaryResponse(summary.getId(), summary.getState(),
        summary.getManager().getUsername(), summary.getDate().toString());
    }
}
