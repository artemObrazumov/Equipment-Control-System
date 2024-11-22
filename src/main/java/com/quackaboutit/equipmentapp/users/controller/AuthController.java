package com.quackaboutit.equipmentapp.users.controller;

import com.quackaboutit.equipmentapp.users.repository.UserRepository;
import com.quackaboutit.equipmentapp.users.request.SignInRequest;
import com.quackaboutit.equipmentapp.users.request.SignUpRequest;
import com.quackaboutit.equipmentapp.users.response.AuthResponse;
import com.quackaboutit.equipmentapp.users.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @Operation(summary = "User registration")
    @PostMapping("/sign-up")
    public AuthResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "User authorization")
    @PostMapping("/sign-in")
    public AuthResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }
}