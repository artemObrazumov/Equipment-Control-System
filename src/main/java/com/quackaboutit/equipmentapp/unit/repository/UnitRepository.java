package com.quackaboutit.equipmentapp.unit.repository;

import com.quackaboutit.equipmentapp.unit.entity.Unit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    @Modifying
    @Query("UPDATE Unit u SET u.address = :address, u.latitude = :latitude, u.longitude = :longitude WHERE u.id = :id")
    void updateUnit(@Param("address") String address, @Param("latitude") Double latitude, 
    @Param("longitude") Double longitude, @Param("id") Long id); 

    List<Unit> findByAddressContaining(String substring);
}
