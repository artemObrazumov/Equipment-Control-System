package com.quackaboutit.equipmentapp.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.equipment.entity.EquipmentType;
import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;

import java.util.List;

@Repository
@Transactional
public interface NamedEquipmentRepository extends JpaRepository<NamedEquipment, Long> {
    @Modifying
    @Query("UPDATE NamedEquipment ne SET ne.licensePlate = :licensePlate, ne.carBrand = :carBrand, ne.base  = :base, ne.equipmentType = :equipmentType WHERE ne.id = :id")
    void updateNamedEquipment(@Param("licensePlate") String license_plate, @Param("carBrand") String carBrand, @Param("base") Base base,
    @Param("equipmentType") EquipmentType equipmentType, @Param("id") Long id);

    @Query("SELECT n FROM NamedEquipment n WHERE n.equipmentType.id = :typeId")
    List<NamedEquipment> findAllByEquipmentTypeId(@Param("typeId") Long typeId);

    @Query("SELECT COUNT(eq) FROM NamedEquipment eq WHERE eq.equipmentType.id = :typeId")
    Integer countByTypeId(@Param("typeId") Long typeId);
}
