package com.quackaboutit.equipmentapp.equipment.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.equipment.dto.NamedEquipmentRequest;
import com.quackaboutit.equipmentapp.equipment.dto.NamedEquipmentResponse;
import com.quackaboutit.equipmentapp.equipment.service.NamedEquipmentService;

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

    @PostMapping
    private NamedEquipmentResponse createfindNamedEquipments(@Validated @RequestBody NamedEquipmentRequest request){
        return namedEquipmentService.create(request);
    }
}
