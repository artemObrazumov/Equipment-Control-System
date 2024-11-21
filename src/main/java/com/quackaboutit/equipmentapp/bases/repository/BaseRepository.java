package com.quackaboutit.equipmentapp.bases.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quackaboutit.equipmentapp.bases.entity.Base;

public interface BaseRepository extends JpaRepository<Base, Long> {

}
