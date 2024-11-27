package com.quackaboutit.equipmentapp.workplace.service;

import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceItemResponse;
import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceRequest;
import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceResponse;
import com.quackaboutit.equipmentapp.workplace.dto.WorkplaceUpdateRequest;
import com.quackaboutit.equipmentapp.workplace.entity.Workplace;
import com.quackaboutit.equipmentapp.workplace.entity.WorkplaceState;
import com.quackaboutit.equipmentapp.workplace.exceptions.WorkplaceNotFound;
import com.quackaboutit.equipmentapp.workplace.repository.WorkplaceRepository;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkPlaceService {
    private final WorkplaceRepository workplaceRepository;

    public List<WorkplaceItemResponse> findWorkPlaces(){
        List<Workplace> workplaces = workplaceRepository.findAll();
        List<WorkplaceItemResponse> workplaceResponses = new ArrayList<>();
        workplaces.forEach(workplace -> {
            workplaceResponses.add(WorkplaceItemResponse.builder()
                                            .id(workplace.getId())
                                            .address(workplace.getAddress())
                                            .requestsProcessed(1) // ???
                                            .requestsSent(2) // ???
                                            .build());
        });

        return workplaceResponses;
    }

    public WorkplaceResponse findWorkplacesById(Long Id) throws WorkplaceNotFound{
        Workplace workplace = workplaceRepository.findById(Id)
                        .orElseThrow(() -> new WorkplaceNotFound());
        
        return WorkplaceResponse.builder()
                        .id(workplace.getId())
                        .state(workplace.getState())
                        .latitude(workplace.getLatitude())
                        .longitude(workplace.getLongitude())
                        .address(workplace.getAddress())
                        .build(); 
    }

    public WorkplaceResponse create(WorkplaceRequest req){ 
        var workplace = workplaceRepository.save(new Workplace(
            null, WorkplaceState.IDLE, req.getLatitude(), req.getLongitude(), req.getAddress()
        ));

        return WorkplaceResponse.builder()
                        .id(workplace.getId())
                        .state(workplace.getState())
                        .latitude(workplace.getLatitude())
                        .longitude(workplace.getLongitude())
                        .address(workplace.getAddress())
                        .build();  
    }

    public void update(Long id, WorkplaceUpdateRequest request){
        WorkplaceState state = (request.getHasStarted() ? WorkplaceState.INWORKS : WorkplaceState.FINISHED);
        
        workplaceRepository.updateWorkplace(request.getAddress(), 
        request.getLatitude(), request.getLongitude(), state, id);
    }

    public void delete(Long id){
        workplaceRepository.deleteById(id);
    }
}
