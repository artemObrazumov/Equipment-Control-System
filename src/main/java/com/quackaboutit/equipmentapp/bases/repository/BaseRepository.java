package com.quackaboutit.equipmentapp.bases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.unit.entity.Unit;

@Transactional
public interface BaseRepository extends JpaRepository<Base, Long> {
    @Modifying
    @Query("UPDATE Base b SET b.address = :address, b.latitude = :latitude, b.longitude = :longitude WHERE b.id = :id")
    void updateBase(@Param("address") String address, @Param("latitude") Double latitude, 
    @Param("longitude") Double longitude, @Param("id") Long id); // b.unit_id = ?4

}
