package com.quackaboutit.equipmentapp.request.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.request.dto.SummaryResponse;
import com.quackaboutit.equipmentapp.request.entity.Summary;
import com.quackaboutit.equipmentapp.request.entity.SummaryState;
import com.quackaboutit.equipmentapp.request.repository.SummaryRepository;
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
}
