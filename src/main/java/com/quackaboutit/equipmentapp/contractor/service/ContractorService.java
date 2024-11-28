package com.quackaboutit.equipmentapp.contractor.service;

import java.util.ArrayList;
import java.util.List;

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

    public ContractorResponse create(ContractorRequest request){
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

    public ContractorResponse getContractorById(Long id){
        var contractor = contractorRepository.findById(id).
            orElseThrow(() -> new ContractorNotFound());
        
        return ContractorResponse.builder()
                        .id(contractor.getId())
                        .name(contractor.getName())
                        .inn(contractor.getInn())
                        .kpp(contractor.getKpp())
                        .legalAddress(contractor.getLegalAddress())
                        .phoneNumber(contractor.getPhoneNumber())
                        .build();
    }

    public List<ContractorResponse> getAllContractors(){
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
