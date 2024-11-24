package com.quackaboutit.equipmentapp.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.quackaboutit.equipmentapp.request.entity.Summary;

public interface SummaryRepository extends JpaRepository<Summary, Long> {

}
