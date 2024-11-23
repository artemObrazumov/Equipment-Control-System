package com.quackaboutit.equipmentapp.workplace.controller;

import java.util.List;

import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceItemResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceRequest;
import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceResponse;
import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceUpdateRequest;
import com.quackaboutit.equipmentapp.workplace.service.WorkPlaceService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/workplaces")
@RequiredArgsConstructor
public class WorkPlaceController {
    private final WorkPlaceService workPlaceService;

    @GetMapping
    public List<WorkplaceItemResponse> findWorkPlaces(){
        return workPlaceService.findWorkPlaces();
    }

    @GetMapping("/{id}")
    public WorkplaceResponse findWorkplaceById(@PathVariable Long id){
        return workPlaceService.findWorkplacesById(id);
    }

    @PostMapping
    public WorkplaceResponse createWorkplace(@Valid @RequestBody WorkplaceRequest req){
        return workPlaceService.create(req);
    }

    @PutMapping("/{id}")
    public void updateWorkplaceById(@PathVariable Long id, @Valid @RequestBody WorkplaceUpdateRequest request){
        workPlaceService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkplaceById(@PathVariable Long id){
        workPlaceService.delete(id);
    }
}
