package com.quackaboutit.equipmentapp.equipment.controller;

import com.quackaboutit.equipmentapp.equipment.dto.EquipmentTypeDetailsResponse;
import org.springframework.web.bind.annotation.*;

import com.quackaboutit.equipmentapp.equipment.dto.EquipmentTypeRequest;
import com.quackaboutit.equipmentapp.equipment.dto.EquipmentTypeResponse;
import com.quackaboutit.equipmentapp.equipment.service.EquipmentTypeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/equipment_type")
@AllArgsConstructor
public class EquipmentTypeController {
    private final EquipmentTypeService equipmentTypeService;

    @PostMapping
    private EquipmentTypeResponse createEquipmentTypeResponse(@Valid @RequestBody EquipmentTypeRequest request){
         return equipmentTypeService.create(request);
    }

    @GetMapping("/{id}")
    private EquipmentTypeDetailsResponse findEquipmentTypeDetails(@PathVariable Long id) {
        return equipmentTypeService.findEquipmentTypeDetails(id);
    }

    @DeleteMapping("/{id}")
    private void deleteEquipmentTypeResponseById(@PathVariable Long id){
        equipmentTypeService.delete(id);
    }
}
