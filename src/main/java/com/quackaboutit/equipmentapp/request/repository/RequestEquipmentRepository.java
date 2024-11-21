package com.quackaboutit.equipmentapp.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;


public interface RequestEquipmentRepository extends JpaRepository<RequestedEquipment, Long> {
    
}
