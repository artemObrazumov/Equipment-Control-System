package com.quackaboutit.equipmentapp.unit.service;

import com.quackaboutit.equipmentapp.unit.dto.UnitRequest;
import com.quackaboutit.equipmentapp.unit.dto.UnitResponse;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.unit.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitResponse create(UnitRequest request) {
        return UnitResponse.fromUnit(unitRepository.save(new Unit(null, request.getName(),
                request.getAddress(), request.getLatitude(), request.getLongitude())));
    }

    public List<UnitResponse> findUnits() {
        List<Unit> units = unitRepository.findAll();
        List<UnitResponse> unitResponses = new ArrayList<>();
        units.forEach(unit -> {
            unitResponses.add(UnitResponse.fromUnit(unit));
        });
        return unitResponses;
    }

    public UnitResponse findUnitById(Long id) {
        Optional<Unit> unit = unitRepository.findById(id);
        if (unit.isPresent()) {
            return UnitResponse.fromUnit(unit.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unit not exists");
    }
}
