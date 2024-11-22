package com.quackaboutit.equipmentapp.workplace.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceRequest;
import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceResponse;
import com.quackaboutit.equipmentapp.workplace.service.WorkPlaceService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/workspaces")
@RequiredArgsConstructor
public class WorkpaceController {
    private final WorkPlaceService workPlaceService;

    @GetMapping
    public List<WorkplaceResponse> findWorkspaces(){
        return workPlaceService.findWorksplaces();
    }

    @GetMapping("/{id}")
    public WorkplaceResponse findWorkplaceById(@PathVariable Long id){
        return workPlaceService.findWorkplacesById(id);
    }

    @PostMapping
    public WorkplaceResponse createWorkplace(@Valid @RequestBody WorkplaceRequest req){
        return workPlaceService.create(req);
    }
}
