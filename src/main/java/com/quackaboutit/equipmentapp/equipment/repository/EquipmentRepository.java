package com.quackaboutit.equipmentapp.equipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quackaboutit.equipmentapp.equipment.entity.Equipment;

@Repository
@Transactional
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Modifying
    @Query("UPDATE Equipment e SET e.name = :name, e.image = :image WHERE e.id = :id")
    void updateEquipment(@Param("name") String name, @Param("image") String image, @Param("id") Long id);
}
