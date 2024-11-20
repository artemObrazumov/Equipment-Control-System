package com.quackaboutit.equipmentapp.request.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quackaboutit.equipmentapp.request.entity.Request;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Request, Long>{
    @Query("SELECT n FROM Request n WHERE n.creator.id = :CreatorId")
    List<Request> findAllByCreatorId(@Param("CreatorId") Long CreatorId);
}
