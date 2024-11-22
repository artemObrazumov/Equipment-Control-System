package com.quackaboutit.equipmentapp.workplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quackaboutit.equipmentapp.workplace.entity.Workplace;

@Repository
@Transactional
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {
    @Modifying
    @Query("UPDATE Workplace w SET w.address = :address, w.latitude = :latitude, w.longitude = :longitude WHERE w.id = :id")
    void updateWorkplace(@Param("address") String address, @Param("latitude") Double latitude, 
    @Param("longitude") Double longitude, @Param("id") Long id); 
}
