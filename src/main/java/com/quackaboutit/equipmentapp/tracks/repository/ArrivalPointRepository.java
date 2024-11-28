package com.quackaboutit.equipmentapp.tracks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quackaboutit.equipmentapp.tracks.entity.ArrivalPoint;

@Repository
public interface ArrivalPointRepository extends JpaRepository<ArrivalPoint, Long>  {

}
