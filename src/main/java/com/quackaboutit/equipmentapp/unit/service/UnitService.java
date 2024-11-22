package com.quackaboutit.equipmentapp.unit.service;

import com.quackaboutit.equipmentapp.unit.dto.UnitRequest;
import com.quackaboutit.equipmentapp.unit.dto.UnitResponse;
import com.quackaboutit.equipmentapp.unit.dto.UnitUpdateRequest;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.unit.exceptions.UnitNotFound;
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
        return UnitResponse.fromUnit(unitRepository.save(new Unit(null,
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

    public UnitResponse findUnitById(Long id) throws UnitNotFound {
        Unit unit = unitRepository.findById(id).orElseThrow(() -> new UnitNotFound());
        
        return UnitResponse.fromUnit(unit);
    }

    public void update(Long id, UnitUpdateRequest request){
        unitRepository.updateUnit(request.getAddress(),
        request.getLatitude(), request.getLongitude(), id);
    }

    public void delete(Long id){
        unitRepository.deleteById(id);
    }
    
}
