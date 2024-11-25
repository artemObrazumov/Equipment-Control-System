package com.quackaboutit.equipmentapp.users.response;

import com.quackaboutit.equipmentapp.users.entity.Role;
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
public class AuthResponse {

    @NotNull
    @NotBlank
    private String token;

    @NotNull
    private Role userRole;
}