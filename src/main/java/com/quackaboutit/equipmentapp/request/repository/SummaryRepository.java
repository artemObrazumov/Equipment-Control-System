package com.quackaboutit.equipmentapp.request.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quackaboutit.equipmentapp.request.entity.Summary;

@Transactional
@Repository
public interface SummaryRepository extends JpaRepository<Summary, Long> {
    @Modifying
    @Query("SELECT s FROM Summary s WHERE s.unit.id = :UnitId")
    List<Summary> findAllSummarysByUnitId(@Param("UnitId") Long unitId);
}
