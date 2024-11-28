package com.quackaboutit.equipmentapp.unit.service;

import com.quackaboutit.equipmentapp.request.repository.RequestRepository;
import com.quackaboutit.equipmentapp.unit.dto.UnitRequest;
import com.quackaboutit.equipmentapp.unit.dto.UnitResponse;
import com.quackaboutit.equipmentapp.unit.dto.UnitUpdateRequest;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.unit.exceptions.UnitNotFound;
import com.quackaboutit.equipmentapp.unit.repository.UnitRepository;
import com.quackaboutit.equipmentapp.workplace.repository.WorkplaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;
    private final WorkplaceRepository workplaceRepository;
    private final RequestRepository requestRepository;

    public UnitResponse create(UnitRequest request) {
        Unit unit = unitRepository.save(new Unit(null,
                request.getAddress(), request.getLatitude(), request.getLongitude()));
        return UnitResponse.builder()
                .id(unit.getId())
                .address(unit.getAddress())
                .latitude(unit.getLatitude())
                .longitude(unit.getLongitude())
                .build();
    }

    public List<UnitResponse> findUnits() {
        List<Unit> units = unitRepository.findAll();
        List<UnitResponse> unitResponses = new ArrayList<>();
        units.forEach(unit -> {
            Integer workplaceCount = workplaceRepository.countByUnitId(unit.getId());
            Integer requestCount = requestRepository.countByUnitId(unit.getId());
            unitResponses.add(
                    UnitResponse.builder()
                            .id(unit.getId())
                            .address(unit.getAddress())
                            .latitude(unit.getLatitude())
                            .longitude(unit.getLongitude())
                            .workplaces(workplaceCount)
                            .requests(requestCount)
                            .build()
            );
        });
        return unitResponses;
    }

    public UnitResponse findUnitById(Long id) throws UnitNotFound {
        Unit unit = unitRepository.findById(id).orElseThrow(() -> new UnitNotFound());

        Integer workplaceCount = workplaceRepository.countByUnitId(unit.getId());
        Integer requestCount = requestRepository.countByUnitId(unit.getId());
        return UnitResponse.builder()
                .id(unit.getId())
                .address(unit.getAddress())
                .latitude(unit.getLatitude())
                .longitude(unit.getLongitude())
                .workplaces(workplaceCount)
                .requests(requestCount)
                .build();
    }

    public List<UnitResponse> findByAddressContaining(String substr) {
        List<Unit> units = unitRepository.findByAddressContaining(substr);
        List<UnitResponse> unitResponses = new ArrayList<>();
        units.forEach(unit -> {
            Integer workplaceCount = workplaceRepository.countByUnitId(unit.getId());
            Integer requestCount = requestRepository.countByUnitId(unit.getId());
            unitResponses.add(
                    UnitResponse.builder()
                            .id(unit.getId())
                            .address(unit.getAddress())
                            .latitude(unit.getLatitude())
                            .longitude(unit.getLongitude())
                            .workplaces(workplaceCount)
                            .requests(requestCount)
                            .build()
            );
        });

        return unitResponses;
    }

    public void update(Long id, UnitUpdateRequest request) {
        unitRepository.updateUnit(request.getAddress(),
                request.getLatitude(), request.getLongitude(), id);
    }

    public void delete(Long id) {
        unitRepository.deleteById(id);
    }

}
