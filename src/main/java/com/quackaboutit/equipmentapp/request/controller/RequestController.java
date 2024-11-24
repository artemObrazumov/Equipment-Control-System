package com.quackaboutit.equipmentapp.request.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quackaboutit.equipmentapp.request.dto.RequestDetailsResponse;
import org.springframework.web.bind.annotation.*;

import com.quackaboutit.equipmentapp.request.dto.RequestForRequest;
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

    private final ObjectMapper objectMapper;

    @GetMapping
    private List<ResponseRequest> getRequests(){
        return requestService.getRequests(
            jwtService.getUserFromSecurityContextHolder().getId());
    }

    @PostMapping
    private ResponseRequest postRequest(@RequestBody String request) throws JsonProcessingException {
        return requestService.postRequest(objectMapper.readValue(request, RequestForRequest.class));
    }

    @GetMapping("/{id}")
    private RequestDetailsResponse getRequestsDetails(@PathVariable Long id){
        return requestService.getRequestDetailById(id);
    }
}
