package com.quackaboutit.equipmentapp.contractor.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.quackaboutit.equipmentapp.equipment.dto.NamedEquipmentResponse;
import com.quackaboutit.equipmentapp.equipment.repository.NamedEquipmentRepository;
import com.quackaboutit.equipmentapp.tracks.dto.TrackResponse;
import com.quackaboutit.equipmentapp.tracks.repository.TrackRepository;
import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.contractor.dto.ContractorNotFound;
import com.quackaboutit.equipmentapp.contractor.dto.ContractorRequest;
import com.quackaboutit.equipmentapp.contractor.dto.ContractorResponse;
import com.quackaboutit.equipmentapp.contractor.entity.Contractor;
import com.quackaboutit.equipmentapp.contractor.repository.ContractorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContractorService {
    private final ContractorRepository contractorRepository;
    private final NamedEquipmentRepository namedEquipmentRepository;
    private final TrackRepository trackRepository;

    public ContractorResponse create(ContractorRequest request) {
        var contractor = contractorRepository.save(new Contractor(
                null, request.getName(), request.getInn(),
                request.getKpp(), request.getLegalAddress(), request.getPhoneNumber()
        ));

        return ContractorResponse.builder()
                .id(contractor.getId())
                .name(contractor.getName())
                .inn(contractor.getInn())
                .kpp(contractor.getKpp())
                .legalAddress(contractor.getLegalAddress())
                .phoneNumber(contractor.getPhoneNumber())
                .build();
    }

    public ContractorResponse getContractorById(Long id) {
        var contractor = contractorRepository.findById(id).
                orElseThrow(() -> new ContractorNotFound());
        var equipment = namedEquipmentRepository.findAllByEquipmentContractorId(id).stream().map(namedEquipment ->
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
                        .paymentHourly(namedEquipment.getPaymentHourly())
                        .condition(namedEquipment.getCondition())
                        .build()
        ).toList();
        var orders = trackRepository.findByContractor(id).stream().map(track ->
                TrackResponse.builder()
                        .licensePlateNumber(track.getLicensePlateNumber())
                        .id(id)
                        .date(track.getDate().toString())
                        .namedEquipment(track.getNamedEquipment())
                        .driver(track.getDriver())
                        .isActive(track.getIsActive())
                        .build()
        ).toList();

        return ContractorResponse.builder()
                .id(contractor.getId())
                .name(contractor.getName())
                .inn(contractor.getInn())
                .kpp(contractor.getKpp())
                .legalAddress(contractor.getLegalAddress())
                .phoneNumber(contractor.getPhoneNumber())
                .equipment(equipment)
                .orders(orders)
                .build();
    }

    public List<ContractorResponse> getAllContractors() {
        List<Contractor> contractors = contractorRepository.findAll();
        List<ContractorResponse> contractorResponses = new ArrayList<>();

        contractors.forEach(contractor -> {
            contractorResponses.add(ContractorResponse.builder()
                    .id(contractor.getId())
                    .name(contractor.getName())
                    .inn(contractor.getInn())
                    .kpp(contractor.getKpp())
                    .legalAddress(contractor.getLegalAddress())
                    .phoneNumber(contractor.getPhoneNumber())
                    .build());
        });

        return contractorResponses;
    }
}
