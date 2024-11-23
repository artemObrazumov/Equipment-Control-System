package com.quackaboutit.equipmentapp.workplace.dto;

import com.quackaboutit.equipmentapp.workplace.entity.Workplace;
import com.quackaboutit.equipmentapp.workplace.entity.WorkplaceState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
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

    public static WorkplaceItemResponse fromWorkPlaceToResponse(
            Workplace workplace, Integer requestsProcessed, Integer requestsSent){
        return new WorkplaceItemResponse(workplace.getId(), workplace.getAddress(),
                requestsProcessed, requestsSent);
    }
}
