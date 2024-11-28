package com.quackaboutit.equipmentapp.request.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.quackaboutit.equipmentapp.request.dto.SummaryIdResponse;
import com.quackaboutit.equipmentapp.request.dto.SummaryResponse;
import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.RequestState;
import com.quackaboutit.equipmentapp.request.entity.Summary;
import com.quackaboutit.equipmentapp.request.entity.SummaryState;
import com.quackaboutit.equipmentapp.request.exceptions.HasOpenedSummary;
import com.quackaboutit.equipmentapp.request.exceptions.RequestNotFound;
import com.quackaboutit.equipmentapp.request.exceptions.SummaryNotFound;
import com.quackaboutit.equipmentapp.request.repository.RequestRepository;
import com.quackaboutit.equipmentapp.request.repository.SummaryRepository;
import com.quackaboutit.equipmentapp.tracks.service.TrackService;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.users.response.UserSummaryResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SummaryService {
    private final SummaryRepository summaryRepository;
    private final RequestRepository requestRepository;
    private final TrackService trackService;

    public SummaryResponse create(User manager) throws RuntimeException{
        Optional<Summary> lastSummary = summaryRepository.findFirstByOrderByIdDesc();
        if(lastSummary.isPresent())
            if(lastSummary.get().getState() == SummaryState.NEW) throw new HasOpenedSummary();
        
        var summary = summaryRepository.save(new Summary(null, SummaryState.NEW, 
        manager, LocalDateTime.now(), manager.getUnit(), new ArrayList<>()));

        return SummaryResponse.builder()
            .id(summary.getId())
            .state(summary.getState())
            .managerName(summary.getManager().getUsername())
            .date(summary.getDate().toString())
            .build();
    }

    public List<SummaryResponse> findAllSummarysByUnitId(Unit unit){
        List<Summary> summaries = summaryRepository.findAllSummarysByUnitId(unit.getId());
        List<SummaryResponse> summaryResponses = new ArrayList<>();
        summaries.forEach(summary -> {
            summaryResponses.add(SummaryResponse.builder()
                                        .id(summary.getId())
                                        .state(summary.getState())
                                        .managerName(summary.getManager().getUsername())
                                        .date(summary.getDate().toString())
                                        .build());
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
        
        if(request.getState() == RequestState.SENT){
            requestRepository.updateState(RequestState.PROCESSING, id);
            lastSummary.get().getRequests().add(request);
            summaryRepository.save(lastSummary.get());

        }else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Request has already been procesed.");

    }
    public SummaryIdResponse getSummaryById(Long id) throws SummaryNotFound{
        Summary summary = summaryRepository.findById(id).orElseThrow(
            () -> new SummaryNotFound()
        );

        var userSummaryResponse = UserSummaryResponse.builder()
                                        .id(summary.getManager().getId())
                                        .username(summary.getManager().getUsername())
                                        .role(summary.getManager().getRole())
                                        .email(summary.getManager().getEmail())
                                        .unit(summary.getManager().getUnit())
                                        .build();

        return SummaryIdResponse.builder()
                            .id(summary.getId())
                            .state(summary.getState())
                            .manager(userSummaryResponse)
                            .date(summary.getDate().toString())
                            .unit(summary.getUnit())
                            .requests(summary.getRequests())
                            .build();
    }

    private SummaryResponse changeStateSummary(Long id, SummaryState state) throws SummaryNotFound{
        Summary summary = summaryRepository.findById(id).
            orElseThrow(() -> new SummaryNotFound());
        
        summaryRepository.updateState(state, id);

        return SummaryResponse.builder()
                        .id(summary.getId())
                        .state(summary.getState())
                        .managerName(summary.getManager().getUsername())
                        .date(summary.getDate().toString())
                        .build();
    } 
    
    public void closeSummary(Long id){
        trackService.create(id);
    }

    public SummaryResponse archiveSummary(Long id){
        return changeStateSummary(id, SummaryState.ARCHIVED);
    }
}
