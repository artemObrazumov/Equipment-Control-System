package com.quackaboutit.equipmentapp.contractor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.contractor.dto.ContractorRequest;
import com.quackaboutit.equipmentapp.contractor.dto.ContractorResponse;
import com.quackaboutit.equipmentapp.contractor.service.ContractorService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/contractor")
@AllArgsConstructor
public class ContractorController {
    private final ContractorService contractorService;

    @PostMapping
    private ContractorResponse create(@Valid @RequestBody ContractorRequest request){
        return contractorService.create(request);
    }

    @GetMapping("/{id}")
    private ContractorResponse getContractorById(@PathVariable Long id){
        return contractorService.getContractorById(id);
    }

    @GetMapping
    private List<ContractorResponse> getAllContractors(){
        return contractorService.getAllContractors();
    }

    //@PostMapping("/{id}/create_request")
    //private 
}
