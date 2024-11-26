package com.quackaboutit.equipmentapp.unit.controller;

import com.quackaboutit.equipmentapp.unit.dto.UnitRequest;
import com.quackaboutit.equipmentapp.unit.dto.UnitResponse;
import com.quackaboutit.equipmentapp.unit.dto.UnitUpdateRequest;
import com.quackaboutit.equipmentapp.unit.service.UnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unit")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    private List<UnitResponse> findUnits() {
        return unitService.findUnits();
    }

    @GetMapping("/{id}")
    private UnitResponse findUnitById(@PathVariable Long id) {
        return unitService.findUnitById(id);
    }

    @GetMapping("/containing/{substr}")
    private List<UnitResponse> findUnitContainsSubstr(@PathVariable String substr) {
        return unitService.findByAddressContaining(substr);
    }

    @PostMapping
    private UnitResponse createUnit(@Valid @RequestBody UnitRequest request) {
        return unitService.create(request);
    }

    @DeleteMapping("/{id}")
    private void deleteUnitbyId(@PathVariable Long id){
        unitService.delete(id);
    }

    @PutMapping("/{id}")
    private void updateUnitById(@PathVariable Long id, @Valid @RequestBody UnitUpdateRequest request){
        unitService.update(id, request);
    }
}
