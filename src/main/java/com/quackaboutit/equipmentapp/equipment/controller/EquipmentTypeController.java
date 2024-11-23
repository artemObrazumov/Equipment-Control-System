package com.quackaboutit.equipmentapp.equipment.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @DeleteMapping("/{id}")
    private void deleteEquipmentTypeResponseById(@PathVariable Long id){
        equipmentTypeService.delete(id);
    }
}
