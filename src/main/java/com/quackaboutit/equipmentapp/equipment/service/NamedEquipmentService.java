package com.quackaboutit.equipmentapp.equipment.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.quackaboutit.equipmentapp.equipment.dto.EquipmentBestOptionsResponse;
import com.quackaboutit.equipmentapp.users.entity.User;
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
import com.quackaboutit.equipmentapp.users.service.JwtService;
import com.quackaboutit.equipmentapp.utils.LicensePlate;

import lombok.AllArgsConstructor;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class NamedEquipmentService {
    private final NamedEquipmentRepository namedEquipmentRepository;
    private final BaseRepository baseRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;
    private final JwtService jwtService;

    public List<NamedEquipmentResponse> findNamedEquipments() {
        List<NamedEquipment> namedEquipments = namedEquipmentRepository.findAll();
        List<NamedEquipmentResponse> namedEquipmentResponses = new ArrayList<>();

        namedEquipments.forEach(namedEquipment -> {
            namedEquipmentResponses.add(NamedEquipmentResponse.builder()
                    .id(namedEquipment.getId())
                    .licensePlate(namedEquipment.getLicensePlate())
                    .carBrand(namedEquipment.getCarBrand())
                    .base(namedEquipment.getBase())
                    .equipmentType(namedEquipment.getEquipmentType())
                    .isActive(true)
                    .lastWorkPlaceAddress("ADDDRESSS")
                    .finishTime(LocalDateTime.now().toString())
                    .fuelType(namedEquipment.getFuelType())
                    .build());
        });

        return namedEquipmentResponses;
    }

    public NamedEquipmentResponse findNamedEquipmentById(Long id) throws EquipmentNotFound {
        var namedEquipment = namedEquipmentRepository.findById(id).orElseThrow(() -> new EquipmentNotFound());

        return NamedEquipmentResponse.builder()
                .id(namedEquipment.getId())
                .licensePlate(namedEquipment.getLicensePlate())
                .carBrand(namedEquipment.getCarBrand())
                .base(namedEquipment.getBase())
                .equipmentType(namedEquipment.getEquipmentType())
                .isActive(true)
                .lastWorkPlaceAddress("ADDDRESSS")
                .finishTime(LocalDateTime.now().toString())
                .fuelType(namedEquipment.getFuelType())
                .build();
    }

    public NamedEquipmentResponse create(NamedEquipmentRequest request) throws RuntimeException {
        LicensePlate.checkLicensePlate(request.getLicensePlate());

        Base base = baseRepository.findById(request.getBaseId())
                .orElseThrow(() -> new BaseNotFound());
        EquipmentType equipmentType = equipmentTypeRepository.findById(request.getEquipmentTypeId())
                .orElseThrow(() -> new EquipmentNotFound());

        var namedEquipment = namedEquipmentRepository.save(
                new NamedEquipment(null, request.getLicensePlate(),
                        request.getCarBrand(), base, request.getFuelType(), null, equipmentType));

        return NamedEquipmentResponse.builder()
                .id(namedEquipment.getId())
                .licensePlate(namedEquipment.getLicensePlate())
                .carBrand(namedEquipment.getCarBrand())
                .base(namedEquipment.getBase())
                .equipmentType(namedEquipment.getEquipmentType())
                .isActive(true)
                .lastWorkPlaceAddress("ADDDRESSS")
                .finishTime(LocalDateTime.now().toString())
                .fuelType(namedEquipment.getFuelType())
                .build();
    }

    public void update(Long id, NamedEquipmentRequest request) {
        LicensePlate.checkLicensePlate(request.getLicensePlate());

        Base base = baseRepository.findById(request.getBaseId())
                .orElseThrow(() -> new BaseNotFound());
        EquipmentType equipmentType = equipmentTypeRepository.findById(request.getEquipmentTypeId())
                .orElseThrow(() -> new EquipmentNotFound());

        namedEquipmentRepository.updateNamedEquipment(request.getLicensePlate(), request.getCarBrand(),
                base, equipmentType, id, request.getFuelType());
    }

    public List<NamedEquipmentResponse> findNamedEquipmentsByBase() {
        var base = baseRepository.findBaseByUnit(jwtService.getUserFromSecurityContextHolder().getUnit());
        List<NamedEquipment> namedEquipments = namedEquipmentRepository.findAllByEquipmentBase(base);
        List<NamedEquipmentResponse> namedEquipmentResponses = new ArrayList<>();

        namedEquipments.forEach(namedEquipment -> {
            namedEquipmentResponses.add(NamedEquipmentResponse.builder()
                    .id(namedEquipment.getId())
                    .licensePlate(namedEquipment.getLicensePlate())
                    .carBrand(namedEquipment.getCarBrand())
                    .base(namedEquipment.getBase())
                    .equipmentType(namedEquipment.getEquipmentType())
                    .isActive(true)
                    .lastWorkPlaceAddress("ADDDRESSS")
                    .finishTime(LocalDateTime.now().toString())
                    .fuelType(namedEquipment.getFuelType())
                    .build());
        });

        return namedEquipmentResponses;
    }

    public void delete(Long id) {
        namedEquipmentRepository.deleteById(id);
    }

    public List<NamedEquipmentResponse> findNamedEquipmentsByContractor(Long id) {
        return namedEquipmentRepository.findAllByEquipmentContractorId(id).stream().map(namedEquipment ->
                NamedEquipmentResponse.builder()
                        .id(namedEquipment.getId())
                        .licensePlate(namedEquipment.getLicensePlate())
                        .carBrand(namedEquipment.getCarBrand())
                        .base(namedEquipment.getBase())
                        .equipmentType(namedEquipment.getEquipmentType())
                        .isActive(true)
                        .lastWorkPlaceAddress("ADDDRESSS")
                        .finishTime(LocalDateTime.now().toString())
                        .fuelType(namedEquipment.getFuelType())
                        .build()
        ).toList();
    }

    public EquipmentBestOptionsResponse getBestEquipmentOptions() {
        User user = jwtService.getUserFromSecurityContextHolder();
        Base base = baseRepository.findBaseByUnit(user.getUnit());
        List<NamedEquipment> onBaseOptions = namedEquipmentRepository.findAllByEquipmentBase(base)
                .stream().sorted(
                        (o1, o2) -> {
                            Integer eq1 = 1;
                            Integer eq2 = 2;
                            return eq1.compareTo(eq2);
                        }
                ).toList();
        List<NamedEquipment> contractorOptions = namedEquipmentRepository.findContractorOptions();

        return EquipmentBestOptionsResponse.builder()
                .onBaseEquipment(
                        onBaseOptions
                                .stream().map(namedEquipment ->
                                        NamedEquipmentResponse.builder()
                                                .id(namedEquipment.getId())
                                                .licensePlate(namedEquipment.getLicensePlate())
                                                .carBrand(namedEquipment.getCarBrand())
                                                .base(namedEquipment.getBase())
                                                .equipmentType(namedEquipment.getEquipmentType())
                                                .isActive(true)
                                                .lastWorkPlaceAddress("ADDDRESSS")
                                                .finishTime(LocalDateTime.now().toString())
                                                .fuelType(namedEquipment.getFuelType())
                                                .build()
                                ).toList()
                )
                .contractorEquipment(contractorOptions
                        .stream().map(namedEquipment ->
                                NamedEquipmentResponse.builder()
                                        .id(namedEquipment.getId())
                                        .licensePlate(namedEquipment.getLicensePlate())
                                        .carBrand(namedEquipment.getCarBrand())
                                        .base(namedEquipment.getBase())
                                        .equipmentType(namedEquipment.getEquipmentType())
                                        .isActive(true)
                                        .lastWorkPlaceAddress("ADDDRESSS")
                                        .finishTime(LocalDateTime.now().toString())
                                        .fuelType(namedEquipment.getFuelType())
                                        .build()
                        ).toList()
                )
                .build();
    }
}
