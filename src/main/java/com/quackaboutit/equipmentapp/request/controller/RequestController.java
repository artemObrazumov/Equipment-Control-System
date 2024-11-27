package com.quackaboutit.equipmentapp.request.controller;

import java.util.List;
import com.quackaboutit.equipmentapp.equipment.dto.RequestedEquipmentResponse;
import com.quackaboutit.equipmentapp.request.dto.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.quackaboutit.equipmentapp.request.service.RequestService;
import com.quackaboutit.equipmentapp.request.service.SummaryService;
import com.quackaboutit.equipmentapp.users.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;
    private final SummaryService summaryService;
    private final JwtService jwtService;

    @GetMapping
    private List<ResponseRequest> getRequests() {
        return requestService.getRequests(
                jwtService.getUserFromSecurityContextHolder().getId());
    }

    @PostMapping("/{id}/add_to_summary")
    private void addRequestToSummary(@PathVariable Long id) {
        summaryService.addRequestToSummary(id, jwtService.getUserFromSecurityContextHolder());
    }
    

    @PostMapping
    private ResponseRequest postRequest(@RequestBody RequestForRequest request) {
        return requestService.postRequest(request);
    }

    @GetMapping("/{id}")
    private RequestDetailsResponse getRequestsDetails(@PathVariable Long id) {
        return requestService.getRequestDetailById(id);
    }

    @PostMapping("/{id}/requestedEquipment")
    private RequestedEquipmentResponse postRequestedEquipment(@Valid @RequestBody RequestedEquipmentRequest request,
                                                              @PathVariable Long id) {
        return requestService.postRequestedEquipment(request, id); // !!!
    }

    @PutMapping("/requestedEquipment/{id}")
    private void UpdateRequestedEquipmentById(@Valid @RequestBody RequestedEquipmentRequest request, @PathVariable Long id) {
        requestService.updateRequestedEquipmentById(request, id); 
    }
}
