package com.quackaboutit.equipmentapp.users.service;

import com.quackaboutit.equipmentapp.users.entity.Role;
import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.users.request.SignInRequest;
import com.quackaboutit.equipmentapp.users.request.SignUpRequest;
import com.quackaboutit.equipmentapp.users.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse signUp(SignUpRequest request) {

        var userRole = Role.ROLE_WORKER;
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(userRole)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new AuthResponse(jwt, userRole, 0);
    }

    public AuthResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        UserDetails userDetails = userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        User userInDatabase = userService.getByUsername(request.getUsername());

        var jwt = jwtService.generateToken(userDetails);
        return new AuthResponse(jwt, userInDatabase.getRole(), 0);
    }
}