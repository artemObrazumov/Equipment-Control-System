package com.quackaboutit.equipmentapp.request.dto;

import com.quackaboutit.equipmentapp.equipment.dto.RequestedEquipmentResponse;
import com.quackaboutit.equipmentapp.request.entity.RequestState;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetailsResponse {

    private Long id;
    private RequestState state;
    private String workerName;
    private String unitAddress;
    private String workplaceAddress;
    private Double distance;
    private String date;
    private Integer progress;
    private Integer total;
    private List<RequestedEquipmentResponse> equipment;
}
