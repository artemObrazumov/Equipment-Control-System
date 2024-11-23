package com.quackaboutit.equipmentapp.equipment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.quackaboutit.equipmentapp.equipment.dto.*;
import com.quackaboutit.equipmentapp.equipment.service.EquipmentService;
import com.quackaboutit.equipmentapp.equipment.service.EquipmentTypeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/equipment")
@AllArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final EquipmentTypeService equipmentTypeService;

    @GetMapping
    private List<EquipmentResponse> findEquipment(){
        return equipmentService.findEquipment();
    }

    @GetMapping("/{id}")
    private EquipmentByIdResponse findEquipmentById(@PathVariable Long id){
        return equipmentService.findEquipmentById(id);
    }

    @GetMapping("/types")
    private List<EquipmentByIdResponse> findEquipmentWithTypes(){
        return equipmentService.findEquipmentWithTypes();
    }

    @PostMapping
    private EquipmentResponse createEquipment(@Valid @RequestBody EquipmentRequest request){
        return equipmentService.create(request);
    }

    @GetMapping("/{id}/types")
    private List<EquipmentTypeResponse> findAllTypesByEquipment(@PathVariable Long id){
        return equipmentTypeService.findAllTypesByEquipment(id);
    }

    @PutMapping("/{id}")
    private void updateEquipmentById(@PathVariable Long id, @Valid @RequestBody EquipmentUpdateRequest request){
        equipmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    private void deleteEquipmentById(@PathVariable Long id){
        equipmentService.delete(id);
    }
}
