package com.quackaboutit.equipmentapp.request.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.request.dto.SummaryResponse;
import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.Summary;
import com.quackaboutit.equipmentapp.request.entity.SummaryState;
import com.quackaboutit.equipmentapp.request.exceptions.RequestNotFound;
import com.quackaboutit.equipmentapp.request.repository.RequestRepository;
import com.quackaboutit.equipmentapp.request.repository.SummaryRepository;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.users.entity.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SummaryService {
    private final SummaryRepository summaryRepository;
    private final RequestRepository requestRepository;

    public SummaryResponse create(User manager){
        return SummaryResponse.fromSummaryToResponse(summaryRepository.save(
            new Summary(null, SummaryState.NEW, 
            manager, LocalDateTime.now(), manager.getUnit(), new HashSet<>())));
    }

    public List<SummaryResponse> findAllSummarysByUnitId(Unit unit){
        Set<Summary> summaries = summaryRepository.findAllSummarysByUnitId(unit.getId());
        List<SummaryResponse> summaryResponses = new ArrayList<>();
        summaries.forEach(summary -> {
            summaryResponses.add(SummaryResponse.fromSummaryToResponse(summary));
        });

        return summaryResponses;
    }

    public void addRequestToSummary(Long id, User manager) throws RuntimeException{
        Optional<Summary> lastSummary = summaryRepository.findFirstByOrderByIdDesc();
        
        if(!lastSummary.isPresent() || lastSummary.get().getState() != SummaryState.NEW){
            create(manager);
            addRequestToSummary(id, manager);
            return;
        }
        Request request = requestRepository.findById(id).orElseThrow(
            () -> new RequestNotFound()
        );

        lastSummary.get().getRequests().add(request);

    }
}
