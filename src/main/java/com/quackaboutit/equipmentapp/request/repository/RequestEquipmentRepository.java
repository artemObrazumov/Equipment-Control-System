package com.quackaboutit.equipmentapp.request.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Duration;
import java.time.LocalDateTime;


public interface RequestEquipmentRepository extends JpaRepository<RequestedEquipment, Long> {

    @Query("UPDATE RequestedEquipment req_eq SET req_eq.equipment.id = :eqTypeId, req_eq.licensePlateNumber = :number, " +
            "req_eq.arrivalTime = :time, req_eq.workDuration = :duration WHERE req_eq.id = :id")
    void updateById(@Param("id") Long id, @Param("eqTypeId") Long equipmentTypeId,
                    @Param("number") String licensePlateNumber, @Param("time") LocalDateTime arrivalTime,
                    @Param("duration") Duration workDuration);
}
