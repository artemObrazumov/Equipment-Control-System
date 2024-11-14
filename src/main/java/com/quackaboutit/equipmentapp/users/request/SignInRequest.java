package com.quackaboutit.equipmentapp.users.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Authentication request")
public class SignInRequest {

    @Schema(description = "Username", example = "Artem")
    @Size(min = 5, max = 50, message = "Username length should be in range 5..50")
    @NotBlank(message = "Username cant be blank")
    private String username;

    @Schema(description = "Password", example = "password")
    @Size(min = 5, max = 255, message = "Username length should be in range 5..255")
    @NotBlank(message = "Password cant be blank")
    private String password;
}