package com.quackaboutit.equipmentapp.contractor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractorRequest {
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
