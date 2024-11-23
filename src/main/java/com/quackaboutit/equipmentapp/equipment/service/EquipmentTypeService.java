package com.quackaboutit.equipmentapp.equipment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.equipment.dto.EquipmentTypeRequest;
import com.quackaboutit.equipmentapp.equipment.dto.EquipmentTypeResponse;
import com.quackaboutit.equipmentapp.equipment.entity.Equipment;
import com.quackaboutit.equipmentapp.equipment.entity.EquipmentType;
import com.quackaboutit.equipmentapp.equipment.exceptions.EquipmentNotFound;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentRepository;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentTypeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EquipmentTypeService {
    private final EquipmentTypeRepository equipmentTypeRepository;
    private final EquipmentRepository equipmentRepository;

    public List<EquipmentTypeResponse> findAllTypesByEquipment(Long equipment_id){
        List<EquipmentType> equipments = equipmentTypeRepository.findAllByEquipmentId(equipment_id);
        List<EquipmentTypeResponse> equipmentTypeResponse = new ArrayList<>();
        equipments.forEach(equipment -> {
            equipmentTypeResponse.add(EquipmentTypeResponse.fromEquipmentTypeToResponse(equipment));
        });

        return equipmentTypeResponse;
    }

    public EquipmentTypeResponse create(EquipmentTypeRequest request) throws EquipmentNotFound{
        Equipment equipment = equipmentRepository.findById(
            request.getEquipmentId()).orElseThrow(() -> new EquipmentNotFound());
        
        return EquipmentTypeResponse.fromEquipmentTypeToResponse(
            equipmentTypeRepository.save(new EquipmentType(
                null, request.getType(), equipment
            )));
    }

    public void delete(Long id){
        equipmentTypeRepository.deleteById(id);
    }


}
