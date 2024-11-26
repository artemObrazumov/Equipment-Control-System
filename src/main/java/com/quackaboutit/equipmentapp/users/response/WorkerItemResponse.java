package com.quackaboutit.equipmentapp.users.response;

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
public class WorkerItemResponse {
    private Long id;
    private String currentWorkPlaceAddress;
    private Integer sentRequest;
    private String workerName;
}
