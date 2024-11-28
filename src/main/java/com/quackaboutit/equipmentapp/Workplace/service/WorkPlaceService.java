package com.quackaboutit.equipmentapp.workplace.service;

import com.quackaboutit.equipmentapp.request.repository.RequestRepository;
import com.quackaboutit.equipmentapp.unit.dto.UnitResponse;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.unit.exceptions.UnitNotFound;
import com.quackaboutit.equipmentapp.unit.repository.UnitRepository;
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
    private final UnitRepository unitRepository;
    private final RequestRepository requestRepository;

    public List<WorkplaceItemResponse> findWorkPlaces(){
        List<Workplace> workplaces = workplaceRepository.findAll();
        List<WorkplaceItemResponse> workplaceResponses = new ArrayList<>();
        workplaces.forEach(workplace -> {
            var unit = UnitResponse.builder()
                            .id(workplace.getUnit().getId())
                            .address(workplace.getUnit().getAddress())
                            .latitude(workplace.getUnit().getLatitude())
                            .longitude(workplace.getUnit().getLongitude())
                            .build();


            workplaceResponses.add(WorkplaceItemResponse.builder()
                                            .id(workplace.getId())
                                            .address(workplace.getAddress())
                                            .requestsProcessed(1) // ???
                                            .requestsSent(2) // ???
                                            .unit(unit)
                                            .build());
        });

        return workplaceResponses;
    }

    public List<WorkplaceItemResponse> findWorkPlacesByUnit(Unit cUnit){
        List<Workplace> workplaces = workplaceRepository.findAllbyUnitId(cUnit.getId());
        List<WorkplaceItemResponse> workplaceResponses = new ArrayList<>();

        workplaces.forEach(workplace -> {
            var unit = UnitResponse.builder()
                            .id(workplace.getUnit().getId())
                            .address(workplace.getUnit().getAddress())
                            .latitude(workplace.getUnit().getLatitude())
                            .longitude(workplace.getUnit().getLongitude())
                            .build();

            workplaceResponses.add(WorkplaceItemResponse.builder()
                                            .id(workplace.getId())
                                            .address(workplace.getAddress())
                                            .requestsProcessed(1) // ???
                                            .requestsSent(2) // ???
                                            .unit(unit)
                                            .build());
        });

        return workplaceResponses;
    }

    public WorkplaceResponse findWorkplacesById(Long Id) throws WorkplaceNotFound{
        Workplace workplace = workplaceRepository.findById(Id)
                        .orElseThrow(() -> new WorkplaceNotFound());

        var unit = UnitResponse.builder()
                .id(workplace.getUnit().getId())
                .address(workplace.getUnit().getAddress())
                .latitude(workplace.getUnit().getLatitude())
                .longitude(workplace.getUnit().getLongitude())
                .build();

        return WorkplaceResponse.builder()
                        .id(workplace.getId())
                        .state(workplace.getState())
                        .latitude(workplace.getLatitude())
                        .longitude(workplace.getLongitude())
                        .address(workplace.getAddress())
                        .build(); 
    }

    public WorkplaceResponse create(WorkplaceRequest req){
        Unit unit = unitRepository.findById(req.getUnitId()).orElseThrow(
            () -> new UnitNotFound()
        );


        var workplace = workplaceRepository.save(new Workplace(
            null, WorkplaceState.IDLE, req.getLatitude(), req.getLongitude(), req.getAddress(), unit
        ));

        return WorkplaceResponse.builder()
                        .id(workplace.getId())
                        .state(workplace.getState())
                        .latitude(workplace.getLatitude())
                        .longitude(workplace.getLongitude())
                        .address(workplace.getAddress())
                        .unit(UnitResponse.builder()
                                .id(unit.getId())
                                .address(unit.getAddress())
                                .longitude(unit.getLongitude())
                                .latitude(unit.getLatitude())
                                .build()
                        )
                        .build();
    }

    public void update(Long id, WorkplaceUpdateRequest request){
        WorkplaceState state = (request.getHasStarted() ? WorkplaceState.INWORKS : WorkplaceState.FINISHED);
        Unit unit = unitRepository.findById(id).orElseThrow(
            () -> new UnitNotFound()
        );

        workplaceRepository.updateWorkplace(request.getAddress(),
        request.getLatitude(), request.getLongitude(), state, unit, id);
    }

    public void delete(Long id){
        workplaceRepository.deleteById(id);
    }
}
