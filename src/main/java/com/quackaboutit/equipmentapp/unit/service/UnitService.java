package com.quackaboutit.equipmentapp.unit.service;

import com.quackaboutit.equipmentapp.unit.dto.UnitRequest;
import com.quackaboutit.equipmentapp.unit.dto.UnitResponse;
import com.quackaboutit.equipmentapp.unit.dto.UnitUpdateRequest;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.unit.exceptions.UnitNotFound;
import com.quackaboutit.equipmentapp.unit.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<UnitResponse> findByAddressContaining(String substr){
        List<Unit> units = unitRepository.findByAddressContaining(substr);
        List<UnitResponse> unitResponses = new ArrayList<>();
        units.forEach(unit -> {
            unitResponses.add(UnitResponse.fromUnit(unit));
        });

        return unitResponses;
    }

    public void update(Long id, UnitUpdateRequest request){
        unitRepository.updateUnit(request.getAddress(),
        request.getLatitude(), request.getLongitude(), id);
    }

    public void delete(Long id){
        unitRepository.deleteById(id);
    }
    
}
