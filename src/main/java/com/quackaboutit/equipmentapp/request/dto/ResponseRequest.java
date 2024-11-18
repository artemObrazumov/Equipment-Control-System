package com.quackaboutit.equipmentapp.request.dto;

import java.time.LocalDateTime;

import com.quackaboutit.equipmentapp.request.entity.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseRequest {
    private Long id;
    private RequestState state;
    private String workplaceAddress;
    private Integer equipmentCount;
    private LocalDateTime date;
    private Integer progress;
    private Integer total;

    public static ResponseRequest fromRequest(Request req){

        return new ResponseRequest(req.getId(), req.getState(),
        req.getWorkplace().getAddress(), req.getRequestedEquipment().size(),
        req.getArrivalDate(), 52, 69);
    }
}