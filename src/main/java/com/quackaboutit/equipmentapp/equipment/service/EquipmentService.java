package com.quackaboutit.equipmentapp.equipment.service;

import java.util.ArrayList;
import java.util.List;

import com.quackaboutit.equipmentapp.equipment.repository.NamedEquipmentRepository;
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
    private final NamedEquipmentRepository namedEquipmentRepository;

    public List<EquipmentResponse> findEquipment(){
        List<Equipment> equipments = equipmentRepository.findAll();
        List<EquipmentResponse> equipmentResponses = new ArrayList<>();

        equipments.forEach(equipment -> {
            equipmentResponses.add(EquipmentResponse.builder()
                                            .id(equipment.getId())
                                            .name(equipment.getName())
                                            .image(equipment.getImage())
                                            .build());
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
                var count = namedEquipmentRepository.countByTypeId(equipmentType.getId());
                equipmentTypeResponses.add(EquipmentTypeResponse.builder()
                                                        .id(equipmentType.getId())
                                                        .type(equipmentType.getType())
                                                        .count(count)
                                                        .build());
            });

            equipmentResponses.add(EquipmentByIdResponse.builder()
                                        .id(equipment.getId())
                                        .name(equipment.getName())
                                        .image(equipment.getImage())
                                        .equipmentTypeResponses(equipmentTypeResponses)
                                        .build()
            );
        });

        return equipmentResponses;
    }

    public EquipmentByIdResponse getEquipmentDetails(Long id) throws EquipmentNotFound{
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() -> new EquipmentNotFound());
        List<EquipmentType> equipmentTypes = equipmentTypeRepository.findAllByEquipmentId(id);
        List<EquipmentTypeResponse> equipmentTypeResponses = new ArrayList<>();

        equipmentTypes.forEach(equipmentType -> {
            equipmentTypeResponses.add(EquipmentTypeResponse.builder()
                                                    .id(equipmentType.getId())
                                                    .type(equipmentType.getType())
                                                    .count(null)
                                                    .build());
        });
        
        return EquipmentByIdResponse.builder()
                            .id(equipment.getId())
                            .name(equipment.getName())
                            .image(equipment.getImage())
                            .equipmentTypeResponses(equipmentTypeResponses)
                            .build();
    }  

    public EquipmentResponse create(EquipmentRequest request){
        var equipment = equipmentRepository.save(new Equipment(null, request.getName(), request.getImage()));

        return EquipmentResponse.builder()
                        .id(equipment.getId())
                        .name(equipment.getName())
                        .image(equipment.getImage())
                        .build();
    }

    public void delete(Long id){
        equipmentRepository.deleteById(id);
    }

    public void update(Long id, EquipmentUpdateRequest request){
        equipmentRepository.updateEquipment(request.getName(), request.getImage(), id);
    }
}
