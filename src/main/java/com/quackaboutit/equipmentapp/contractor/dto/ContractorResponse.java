package com.quackaboutit.equipmentapp.contractor.dto;

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
public class ContractorResponse {
    @NotNull
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String inn;

    @NotBlank
    @NotNull
    private String kpp;

    @NotBlank
    @NotNull
    private String legalAddress;

    @NotBlank
    @NotNull
    private String phoneNumber;
}
