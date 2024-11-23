package com.quackaboutit.equipmentapp.equipment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.equipment.dto.EquipmentByIdResponse;
import com.quackaboutit.equipmentapp.equipment.dto.EquipmentRequest;
import com.quackaboutit.equipmentapp.equipment.dto.EquipmentResponse;
import com.quackaboutit.equipmentapp.equipment.dto.EquipmentTypeResponse;
import com.quackaboutit.equipmentapp.equipment.dto.EquipmentUpdateRequest;
import com.quackaboutit.equipmentapp.equipment.entity.Equipment;
import com.quackaboutit.equipmentapp.equipment.entity.EquipmentType;
import com.quackaboutit.equipmentapp.equipment.exceptions.EquipmentNotFound;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentRepository;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentTypeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;

    public List<EquipmentResponse> findEquipment(){
        List<Equipment> equipments = equipmentRepository.findAll();
        List<EquipmentResponse> equipmentResponses = new ArrayList<>();

        equipments.forEach(equipment -> {
            equipmentResponses.add(EquipmentResponse.fromEquipmentToResponse(equipment));
        });

        return equipmentResponses;
    }

    public List<EquipmentByIdResponse> findEquipmentWithTypes(){
        List<Equipment> equipments = equipmentRepository.findAll();
        List<EquipmentByIdResponse> equipmentResponses = new ArrayList<>();

        equipments.forEach(equipment -> {
            List<EquipmentType> equipmentTypes = equipmentTypeRepository.findAllByEquipmentId(equipment.getId());
            List<EquipmentTypeResponse> equipmentTypeResponses = new ArrayList<>();

            equipmentTypes.forEach(equipmentType -> {
                equipmentTypeResponses.add(EquipmentTypeResponse.
                fromEquipmentTypeToResponse(equipmentType));
            });

            equipmentResponses.add(EquipmentByIdResponse.fromEquipmentToResponse(equipment, equipmentTypeResponses));
        });

        return equipmentResponses;
    }

    public EquipmentByIdResponse findEquipmentById(Long id) throws EquipmentNotFound{
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new EquipmentNotFound());
        List<EquipmentType> equipmentTypes = equipmentTypeRepository.findAllByEquipmentId(id);
        List<EquipmentTypeResponse> equipmentTypeResponses = new ArrayList<>();

        equipmentTypes.forEach(equipmentType -> {
            equipmentTypeResponses.add(EquipmentTypeResponse.
            fromEquipmentTypeToResponse(equipmentType));
        });
        
        return EquipmentByIdResponse.fromEquipmentToResponse(equipment, equipmentTypeResponses);
    }  

    public EquipmentResponse create(EquipmentRequest request){
        return EquipmentResponse.fromEquipmentToResponse(equipmentRepository.save(
            new Equipment(null, request.getName(), request.getImage())));
    }

    public void delete(Long id){
        equipmentRepository.deleteById(id);
    }

    public void update(Long id, EquipmentUpdateRequest request){
        equipmentRepository.updateEquipment(request.getName(), request.getImage(), id);
    }
}
