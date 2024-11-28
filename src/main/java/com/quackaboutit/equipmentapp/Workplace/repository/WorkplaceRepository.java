package com.quackaboutit.equipmentapp.workplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.workplace.entity.Workplace;
import com.quackaboutit.equipmentapp.workplace.entity.WorkplaceState;

@Repository
@Transactional
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {
    @Modifying
    @Query("UPDATE Workplace w SET w.address = :address, w.state = :state, w.latitude = :latitude, w.unit = :unit, w.longitude = :longitude WHERE w.id = :id")
    void updateWorkplace(@Param("address") String address, @Param("latitude") Double latitude, 
    @Param("longitude") Double longitude, @Param("state")WorkplaceState state, @Param("unit") Unit unit, @Param("id") Long id); 

    @Query("SELECT w FROM Workplace w WHERE w.unit.id = :unitId")
    List<Workplace> findAllbyUnitId(@Param("unitId") Long UnitId);

    @Query("SELECT COUNT(w) FROM Workplace w WHERE w.unit.id = :id")
    Integer countByUnitId(@Param("id") Long unitId);
}
