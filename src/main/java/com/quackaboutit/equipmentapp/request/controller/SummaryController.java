package com.quackaboutit.equipmentapp.request.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.request.dto.SummaryIdResponse;
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

    @GetMapping
    public List<SummaryResponse> findAllSummarysByUnitId(){
        return summaryService.findAllSummarysByUnitId(jwtService.getUserFromSecurityContextHolder().getUnit());
    }
    
    @PutMapping("/{id}/close")
    private void closeSummary(@PathVariable Long id) {
        summaryService.closeSummary(id);
    }

    @PutMapping("/{id}/archive")
    private SummaryResponse archiveSummary(@PathVariable Long id) {
        return summaryService.archiveSummary(id);
    }

    @GetMapping("/{id}")
    private SummaryIdResponse getSummaryById(@PathVariable Long id){
        return summaryService.getSummaryById(id);
    }

}
