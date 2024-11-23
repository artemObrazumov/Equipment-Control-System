package com.quackaboutit.equipmentapp.equipment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.equipment.dto.EquipmentResponse;
import com.quackaboutit.equipmentapp.equipment.entity.Equipment;
import com.quackaboutit.equipmentapp.equipment.exceptions.EquipmentNotFound;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public List<EquipmentResponse> findEquipment(){
        List<Equipment> equipments = equipmentRepository.findAll();
        List<EquipmentResponse> equipmentResponses = new ArrayList<>();

        equipments.forEach(equipment -> {
            equipmentResponses.add(EquipmentResponse.fromEquipmentToResponse(equipment));
        });

        return equipmentResponses;
    }

    public EquipmentResponse findEquipmentById(Long id) throws EquipmentNotFound{
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new EquipmentNotFound());
        
        return EquipmentResponse.fromEquipmentToResponse(equipment);
    }   
}
