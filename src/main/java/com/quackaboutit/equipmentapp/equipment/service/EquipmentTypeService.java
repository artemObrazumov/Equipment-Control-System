package com.quackaboutit.equipmentapp.equipment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.quackaboutit.equipmentapp.equipment.dto.EquipmentTypeAnalytics;
import com.quackaboutit.equipmentapp.equipment.dto.EquipmentTypeDetailsResponse;
import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;
import com.quackaboutit.equipmentapp.equipment.repository.NamedEquipmentRepository;
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
    private final NamedEquipmentRepository namedEquipmentRepository;

    public List<EquipmentTypeResponse> findAllTypesByEquipment(Long equipment_id){
        List<EquipmentType> equipments = equipmentTypeRepository.findAllByEquipmentId(equipment_id);
        List<EquipmentTypeResponse> equipmentTypeResponse = new ArrayList<>();
        equipments.forEach(equipment -> {
            equipmentTypeResponse.add(EquipmentTypeResponse.builder()
                                                    .id(equipment.getId())
                                                    .type(equipment.getType())
                                                    .count(null)
                                                    .build());
        });

        return equipmentTypeResponse;
    }

    public EquipmentTypeResponse create(EquipmentTypeRequest request) throws EquipmentNotFound{
        Equipment equipment = equipmentRepository.findById(
            request.getEquipmentId()).orElseThrow(() -> new EquipmentNotFound());
        
        EquipmentType equipmentType = equipmentTypeRepository.save(new EquipmentType(
            null, request.getType(), equipment));

        return EquipmentTypeResponse.builder()
                            .id(equipmentType.getId())
                            .type(equipmentType.getType())
                            .count(null)
                            .build();
    }

    public void delete(Long id){
        equipmentTypeRepository.deleteById(id);
    }


    public EquipmentTypeDetailsResponse findEquipmentTypeDetails(Long id) {
        EquipmentType equipmentType = equipmentTypeRepository.findById(id).orElseThrow();
        Equipment equipment = equipmentRepository.findById(id).orElseThrow();
        
        List<NamedEquipment> namedEquipmentList = namedEquipmentRepository.findAllByEquipmentTypeId(id);
        HashMap<String, Integer> brands = new HashMap<>();
        
        AtomicInteger equipmentCount = new AtomicInteger();
        namedEquipmentList.forEach(eq -> {
            brands.put(eq.getCarBrand(), brands.getOrDefault(eq.getCarBrand(), 0) + 1);
            equipmentCount.getAndIncrement();
        });
        EquipmentTypeAnalytics analytics = EquipmentTypeAnalytics.builder()
                .carBrand(brands)
                .equipmentCount(equipmentCount.get())
                .yearActivity(List.of(8,9,12,8,10,12,11,8,9,12,10,12))
                .build();

        return EquipmentTypeDetailsResponse.builder()
                .id(equipmentType.getId())
                .equipmentName(equipment.getName())
                .type(equipmentType.getType())
                .analytics(analytics)
                .build();
    }
}
