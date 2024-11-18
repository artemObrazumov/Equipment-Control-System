package com.quackaboutit.equipmentapp.request.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;

public interface RequestEquipmentRepository extends JpaRepository<RequestedEquipment, Long> {
    
}
