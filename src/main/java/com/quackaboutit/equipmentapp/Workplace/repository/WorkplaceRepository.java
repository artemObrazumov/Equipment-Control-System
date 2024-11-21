package com.quackaboutit.equipmentapp.Workplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quackaboutit.equipmentapp.Workplace.entity.Workplace;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {

}
