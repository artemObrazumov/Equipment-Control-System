package com.quackaboutit.equipmentapp.request.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.request.dto.ResponseRequest;
import com.quackaboutit.equipmentapp.request.service.RequestService;
import com.quackaboutit.equipmentapp.users.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;
    private final JwtService jwtService;

    @GetMapping
    private List<ResponseRequest> getRequests(){
        return requestService.getRequests(
            jwtService.getUserFromSecurityContextHolder().getId());
    }

    @PostMapping
    private List<ResponseRequest> postRequests(){
        return requestService.getRequests(
            jwtService.getUserFromSecurityContextHolder().getId());
    }
}
