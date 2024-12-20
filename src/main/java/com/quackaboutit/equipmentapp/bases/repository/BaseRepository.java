package com.quackaboutit.equipmentapp.bases.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.unit.entity.Unit;

@Transactional
@Repository
public interface BaseRepository extends JpaRepository<Base, Long> {
    @Modifying
    @Query("UPDATE Base b SET b.address = :address, b.latitude = :latitude, b.unit = :unit, b.longitude = :longitude WHERE b.id = :id")
    void updateBase(@Param("address") String address, @Param("latitude") Double latitude, 
    @Param("longitude") Double longitude, @Param("unit") Unit unit , @Param("id") Long id);

    @Query("SELECT b FROM Base b WHERE b.unit = :unit")
    Base findBaseByUnit(@Param("unit") Unit unit);

    List<Base> findByAddressContaining(String substring);
}
