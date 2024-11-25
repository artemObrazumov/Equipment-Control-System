package com.quackaboutit.equipmentapp.request.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.Summary;
import com.quackaboutit.equipmentapp.request.entity.SummaryState;

@Transactional
@Repository
public interface SummaryRepository extends JpaRepository<Summary, Long> {
    @Query("SELECT s FROM Summary s WHERE s.unit.id = :UnitId")
    List<Summary> findAllSummarysByUnitId(@Param("UnitId") Long unitId);
    
    @Query("SELECT s FROM Summary s ORDER BY s.id DESC")
    Optional<Summary> findFirstByOrderByIdDesc();

    @Modifying
    @Query("UPDATE Summary s SET s.requests = :requests WHERE s.id = :id")
    void updateRequests(@Param("requests") List<Request> requests, @Param("id") Long id);

    @Modifying
    @Query("UPDATE Summary s SET s.state = :state WHERE s.id = :id")
    void updateState(@Param("state") SummaryState state, @Param("id") Long id);

}
