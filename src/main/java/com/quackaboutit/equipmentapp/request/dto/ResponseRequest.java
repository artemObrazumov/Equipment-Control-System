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
    private String workerName;
    private String workplaceAddress;
    private Integer equipmentCount;
    private String date;
    private Integer progress;
    private Integer total;

    public static ResponseRequest fromRequest(Request req) {

        return new ResponseRequest(req.getId(), req.getState(),
                req.getCreator().getUsername(), req.getWorkplace().getAddress(),
                req.getRequestedEquipment().size(), req.getArrivalDate().toString(), 52, 69);
    }
}