package com.quackaboutit.equipmentapp.request.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.request.dto.SummaryResponse;
import com.quackaboutit.equipmentapp.request.service.SummaryService;
import com.quackaboutit.equipmentapp.users.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/summary")
@RequiredArgsConstructor
public class SummaryController {
    private final JwtService jwtService;
    private final SummaryService summaryService;

    @PostMapping
    public SummaryResponse createSummary(){
        return summaryService.create(jwtService.getUserFromSecurityContextHolder());
    }


}
