package com.quackaboutit.equipmentapp.equipment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quackaboutit.equipmentapp.equipment.entity.EquipmentType;

@Repository
@Transactional
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Long> {
    @Query("SELECT eq FROM EquipmentType eq WHERE eq.equipment.id = :EquipmentId")
    List<EquipmentType> findAllByEquipmentId(@Param("EquipmentId") Long EquipmentId);
}
