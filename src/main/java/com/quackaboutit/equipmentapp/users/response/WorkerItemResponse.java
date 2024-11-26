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

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    private Long id;

    @NotNull
    private String currentWorkPlaceAddress;

    @NotNull
    private Integer sentRequest;
}
