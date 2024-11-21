package com.quackaboutit.equipmentapp.workplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quackaboutit.equipmentapp.workplace.entity.Workplace;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {

}
