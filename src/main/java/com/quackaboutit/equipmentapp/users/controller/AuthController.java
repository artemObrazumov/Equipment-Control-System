package com.quackaboutit.equipmentapp.users.controller;

import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.users.repository.UserRepository;
import com.quackaboutit.equipmentapp.users.request.SignInRequest;
import com.quackaboutit.equipmentapp.users.request.SignUpRequest;
import com.quackaboutit.equipmentapp.users.response.JwtAuthResponse;
import com.quackaboutit.equipmentapp.users.service.AuthenticationService;
import com.quackaboutit.equipmentapp.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    @Operation(summary = "User registration")
    @PostMapping("/sign-up")
    public JwtAuthResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "User authorization")
    @PostMapping("/sign-in")
    public JwtAuthResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @Operation(summary = "Users")
    @PostMapping("/users")
    public List<User> users() {
        return userRepository.findAll();
    }
}