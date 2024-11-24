package com.quackaboutit.equipmentapp.request.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.request.dto.SummaryResponse;
import com.quackaboutit.equipmentapp.request.entity.Summary;
import com.quackaboutit.equipmentapp.request.entity.SummaryState;
import com.quackaboutit.equipmentapp.request.repository.SummaryRepository;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.users.entity.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SummaryService {
    private final SummaryRepository summaryRepository;

    public SummaryResponse create(User manager){
        return SummaryResponse.fromSummaryToResponse(summaryRepository.save(
            new Summary(null, SummaryState.NEW, 
            manager, LocalDateTime.now(), manager.getUnit(), new ArrayList<>())));
    }

    public List<SummaryResponse> findAllSummarysByUnitId(Unit unit){
        List<Summary> summaries = summaryRepository.findAllSummarysByUnitId(unit.getId());
        List<SummaryResponse> summaryResponses = new ArrayList<>();
        summaries.forEach(summary -> {
            summaryResponses.add(SummaryResponse.fromSummaryToResponse(summary));
        });

        return summaryResponses;
    }
}
