package com.quackaboutit.equipmentapp.equipment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.equipment.dto.NamedEquipmentRequest;
import com.quackaboutit.equipmentapp.equipment.dto.NamedEquipmentResponse;
import com.quackaboutit.equipmentapp.equipment.service.NamedEquipmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/named_equipment")
@AllArgsConstructor
public class NamedEquipmentController {
    private final NamedEquipmentService namedEquipmentService;
    

    @GetMapping
    private List<NamedEquipmentResponse> findNamedEquipments(){
        return namedEquipmentService.findNamedEquipments();
    }

    @GetMapping("/base")
    private List<NamedEquipmentResponse> findNamedEquipmentsByBase(){
        return namedEquipmentService.findNamedEquipmentsByBase();
    }

    @GetMapping("/{id}")
    private NamedEquipmentResponse findNamedEquipmentById(@PathVariable Long id){
        return namedEquipmentService.findNamedEquipmentById(id);
    }

    @PostMapping
    private NamedEquipmentResponse createNamedEquipments(@Valid @RequestBody NamedEquipmentRequest request){
        return namedEquipmentService.create(request);
    }

    @PutMapping("/{id}")
    private void updateNamedEquipment(@PathVariable Long id, @Valid @RequestBody NamedEquipmentRequest request){
        namedEquipmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    private void deleteNamedEquipment(@PathVariable Long id){
        namedEquipmentService.delete(id);
    }
}
