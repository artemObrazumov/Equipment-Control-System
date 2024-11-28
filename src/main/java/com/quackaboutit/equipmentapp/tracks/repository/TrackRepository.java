package com.quackaboutit.equipmentapp.tracks.repository;

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
}
