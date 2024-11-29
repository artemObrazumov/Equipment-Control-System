package com.quackaboutit.equipmentapp.request.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Duration;
import java.time.LocalDateTime;

@Transactional
public interface RequestEquipmentRepository extends JpaRepository<RequestedEquipment, Long> {

    @Modifying
    @Query("UPDATE RequestedEquipment req_eq SET req_eq.equipmentType.id = :eqTypeId, req_eq.licensePlateNumber = :number, " +
            "req_eq.arrivalTime = :time, req_eq.workDuration = :duration WHERE req_eq.id = :id")
    void updateById(@Param("id") Long id, @Param("eqTypeId") Long equipmentTypeId,
                    @Param("number") String licensePlateNumber, @Param("time") LocalDateTime arrivalTime,
                    @Param("duration") Duration workDuration);
}
