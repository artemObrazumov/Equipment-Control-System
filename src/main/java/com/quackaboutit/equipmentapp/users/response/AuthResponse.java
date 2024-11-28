package com.quackaboutit.equipmentapp.users.response;

import com.quackaboutit.equipmentapp.users.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    private Integer test = 0;
}