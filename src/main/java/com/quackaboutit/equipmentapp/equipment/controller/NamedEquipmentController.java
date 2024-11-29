package com.quackaboutit.equipmentapp.equipment.controller;

import java.util.List;

import com.quackaboutit.equipmentapp.equipment.dto.EquipmentBestOptionsResponse;
import com.quackaboutit.equipmentapp.tracks.dto.ArrivalPointResponse;
import org.springframework.web.bind.annotation.*;

import com.quackaboutit.equipmentapp.equipment.dto.NamedEquipmentRequest;
import com.quackaboutit.equipmentapp.equipment.dto.NamedEquipmentResponse;
import com.quackaboutit.equipmentapp.equipment.service.NamedEquipmentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/named_equipment")
@AllArgsConstructor
public class NamedEquipmentController {
    private final NamedEquipmentService namedEquipmentService;
    

    @GetMapping
    private List<NamedEquipmentResponse> findNamedEquipments(){
        return namedEquipmentService.findNamedEquipments();
    }

    @GetMapping("/base")
    private List<NamedEquipmentResponse> findNamedEquipmentsByBase(){
        return namedEquipmentService.findNamedEquipmentsByBase();
    }

    @GetMapping("/contractor/{id}")
    private List<NamedEquipmentResponse> findNamedEquipmentsByContractor(@PathVariable Long id){
        return namedEquipmentService.findNamedEquipmentsByContractor(id);
    }

    @GetMapping("/{id}")
    private NamedEquipmentResponse findNamedEquipmentById(@PathVariable Long id){
        return namedEquipmentService.findNamedEquipmentById(id);
    }

    @PostMapping
    private NamedEquipmentResponse createNamedEquipments(@Valid @RequestBody NamedEquipmentRequest request){
        return namedEquipmentService.create(request);
    }

    @PutMapping("/{id}")
    private void updateNamedEquipment(@PathVariable Long id, @Valid @RequestBody NamedEquipmentRequest request){
        namedEquipmentService.update(id, request);
    }

    @DeleteMapping("/{id}")
    private void deleteNamedEquipment(@PathVariable Long id){
        namedEquipmentService.delete(id);
    }

    @GetMapping("/best")
    private EquipmentBestOptionsResponse getBestEquipment() {
        return namedEquipmentService.getBestEquipmentOptions();
    }

    @GetMapping("/{id}/calendar")
    private List<ArrivalPointResponse> getCalendarForTimestamp(@PathVariable Long id, @RequestParam Long timestamp) {
        return namedEquipmentService.getTimetableOnDay(id, timestamp);
    }
}
