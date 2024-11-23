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
import java.util.Optional;

@Service
@AllArgsConstructor
public class WorkPlaceService {
    private final WorkplaceRepository workplaceRepository;

    public List<WorkplaceItemResponse> findWorkPlaces(){
        List<Workplace> workplaces = workplaceRepository.findAll();
        List<WorkplaceItemResponse> workplaceResponses = new ArrayList<>();
        workplaces.forEach(workplace -> {
            workplaceResponses.add(WorkplaceItemResponse.fromWorkPlaceToResponse(workplace,
                    1, 2));
        });

        return workplaceResponses;
    }

    public WorkplaceResponse findWorkplacesById(Long Id) throws WorkplaceNotFound{
        Optional<Workplace> workplace = workplaceRepository.findById(Id);
        if(workplace.isPresent())
            return WorkplaceResponse.fromWorkplacetoResponce(workplace.get());
        throw new WorkplaceNotFound();
    }

    public WorkplaceResponse create(WorkplaceRequest req){ 
        return WorkplaceResponse.fromWorkplacetoResponce(workplaceRepository.save(new Workplace(
            null, WorkplaceState.IDLE, req.getLatitude(), req.getLongitude(), req.getAddress()
        )));  
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
