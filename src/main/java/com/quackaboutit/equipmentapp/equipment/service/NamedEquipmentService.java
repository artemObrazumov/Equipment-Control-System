package com.quackaboutit.equipmentapp.equipment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.bases.exceptions.BaseNotFound;
import com.quackaboutit.equipmentapp.bases.repository.BaseRepository;
import com.quackaboutit.equipmentapp.equipment.dto.NamedEquipmentRequest;
import com.quackaboutit.equipmentapp.equipment.dto.NamedEquipmentResponse;
import com.quackaboutit.equipmentapp.equipment.entity.EquipmentType;
import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;
import com.quackaboutit.equipmentapp.equipment.exceptions.EquipmentNotFound;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentTypeRepository;
import com.quackaboutit.equipmentapp.equipment.repository.NamedEquipmentRepository;
import com.quackaboutit.equipmentapp.utils.LicensePlate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NamedEquipmentService {
    private final NamedEquipmentRepository namedEquipmentRepository;
    private final BaseRepository baseRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;

    public List<NamedEquipmentResponse> findNamedEquipments(){
        List<NamedEquipment> namedEquipments = namedEquipmentRepository.findAll();
        List<NamedEquipmentResponse> namedEquipmentResponses = new ArrayList<>();

        namedEquipments.forEach(namedEquipment -> {
            namedEquipmentResponses.add(NamedEquipmentResponse.
            fromNamedEquipmentToResponse(namedEquipment));
        });

        return namedEquipmentResponses;
    }

    public NamedEquipmentResponse create(NamedEquipmentRequest request) throws RuntimeException{
        LicensePlate.checkLicensePlate(request.getLicensePlate());
        
        Base base = baseRepository.findById(request.getBaseId())
            .orElseThrow(() -> new BaseNotFound());
        EquipmentType equipmentType = equipmentTypeRepository.findById(request.getEquipmentTypeId())
            .orElseThrow(() -> new EquipmentNotFound());
        
        return NamedEquipmentResponse.fromNamedEquipmentToResponse(namedEquipmentRepository.save(
            new NamedEquipment(null, request.getLicensePlate(), base, equipmentType)
        ));
    }
}
