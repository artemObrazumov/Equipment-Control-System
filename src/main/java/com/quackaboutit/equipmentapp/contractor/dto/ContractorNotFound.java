package com.quackaboutit.equipmentapp.contractor.dto;

public class ContractorNotFound extends RuntimeException {
    public ContractorNotFound(){
        super("Contractor not found.");
    }
}
