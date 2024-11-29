package com.quackaboutit.equipmentapp.tracks.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quackaboutit.equipmentapp.tracks.entity.ArrivalPoint;

@Repository
@Transactional
public interface ArrivalPointRepository extends JpaRepository<ArrivalPoint, Long>  {
    @Modifying
    @Query("UPDATE ArrivalPoint a SET a.realArrivalTime = :realArrivalTime, a.realOutTime = :realOutTime, a.kmOnStart = :kmOnStart, a.kmOnEnd = :kmOnEnd, a.fuelOnStart = :fuelOnStart, a.fuelOnEnd = :fuelOnEnd, a.waitTime = :waitTime WHERE a.id = :id")
    void updateTrackByUserData(@Param("realArrivalTime") LocalDateTime realArrivalTime, 
    @Param("realOutTime") LocalDateTime realOutTime, @Param("kmOnStart") Double kmOnStart, @Param("kmOnEnd") Double kmOnEnd, 
    @Param("fuelOnStart") Double fuelOnStart, @Param("fuelOnEnd") Double fuelOnEnd, @Param("waitTime") LocalDateTime waitTime, @Param("id") Long id);

    @Query("SELECT p FROM ArrivalPoint p WHERE p.planArrivalTime > :start AND p.planArrivalTime < :end")
    List<ArrivalPoint> findByAndTimestamp(@Param("start")LocalDateTime start, @Param("end")LocalDateTime end);

    List<ArrivalPoint> findByAddress(String address);
}
