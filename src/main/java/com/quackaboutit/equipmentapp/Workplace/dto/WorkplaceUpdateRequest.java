package com.quackaboutit.equipmentapp.workplace.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkplaceUpdateRequest extends WorkplaceRequest {
    @NotNull
    Boolean hasStarted;
}
