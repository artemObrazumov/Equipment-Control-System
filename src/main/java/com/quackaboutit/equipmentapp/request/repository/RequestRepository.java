package com.quackaboutit.equipmentapp.request.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.RequestState;

import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface RequestRepository extends JpaRepository<Request, Long>{
    @Query("SELECT n FROM Request n WHERE n.creator.id = :CreatorId")
    List<Request> findAllByCreatorId(@Param("CreatorId") Long CreatorId);

    @Query("SELECT COUNT(r) FROM Request r WHERE r.state = SENT AND r.creator.id = :userId")
    Integer countSentByUser(@Param("userId") Long id);

    @Modifying
    @Query("UPDATE Request r SET r.state = :state WHERE r.id = :id")
    void updateState(@Param("state") RequestState state, @Param("id") Long id);
}
