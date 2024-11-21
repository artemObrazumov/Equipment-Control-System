package com.quackaboutit.equipmentapp.unit.repository;

import com.quackaboutit.equipmentapp.unit.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
}
