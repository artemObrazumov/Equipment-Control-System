package com.quackaboutit.equipmentapp.tracks.repository;

import com.quackaboutit.equipmentapp.tracks.entity.ArrivalPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.quackaboutit.equipmentapp.tracks.entity.Track;

import jakarta.websocket.server.PathParam;

@Repository
@Transactional
public interface TrackRepository extends JpaRepository<Track, Long> {
    @Modifying
    @Query("UPDATE Track t SET t.isActive = false WHERE t.id = :id")
    void closeTrack(@PathParam("id") Long id);

    @Modifying
    @Query("UPDATE Track t SET t.driver = :driver WHERE t.id = :id")
    void updateDriver(@PathParam("driver") String driver, @PathParam("id") Long id);

    @Query("SELECT t From Track t WHERE :point MEMBER OF t.arrivalPoint")
    Track findByArrivalPoint(@PathParam("point") ArrivalPoint point);
}
