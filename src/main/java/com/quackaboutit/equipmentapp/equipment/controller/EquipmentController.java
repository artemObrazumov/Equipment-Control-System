package com.quackaboutit.equipmentapp.equipment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.quackaboutit.equipmentapp.equipment.dto.EquipmentResponse;
import com.quackaboutit.equipmentapp.equipment.service.EquipmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/equipment")
@AllArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping
    private List<EquipmentResponse> findEquipment(){
        return equipmentService.findEquipment();
    }

    @GetMapping("/{id}")
    private EquipmentResponse findEquipmentById(@PathVariable Long id){
        return equipmentService.findEquipmentById(id);
    }
}
