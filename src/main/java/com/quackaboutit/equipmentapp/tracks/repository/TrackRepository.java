package com.quackaboutit.equipmentapp.tracks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quackaboutit.equipmentapp.tracks.entity.Track;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

}
