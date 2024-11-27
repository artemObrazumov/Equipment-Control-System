package com.quackaboutit.equipmentapp.contractor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quackaboutit.equipmentapp.contractor.entity.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {

}
