package com.quackaboutit.equipmentapp.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;

public interface NamedEquipmentRepository extends JpaRepository<NamedEquipment, Long> {

}
