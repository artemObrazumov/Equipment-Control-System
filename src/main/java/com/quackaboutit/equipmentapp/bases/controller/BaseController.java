package com.quackaboutit.equipmentapp.bases.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quackaboutit.equipmentapp.bases.dto.BaseRequest;
import com.quackaboutit.equipmentapp.bases.dto.BaseResponse;
import com.quackaboutit.equipmentapp.bases.dto.BaseUpdateRequest;
import com.quackaboutit.equipmentapp.bases.service.BaseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bases")
@RequiredArgsConstructor
public class BaseController {
    private final BaseService baseService;

    @GetMapping
    public List<BaseResponse> findBases(){
        return baseService.findBases();
    }

    @GetMapping("/{id}") 
    public BaseResponse findBaseById(@PathVariable Long id){
        return baseService.findBaseById(id);
    }

    @PutMapping("/{id}") 
    public void updateBaseById(@PathVariable Long id, @Valid @RequestBody BaseUpdateRequest request){
        baseService.update(id, request);
    }

    @PostMapping
    public BaseResponse createBase(@Valid @RequestBody BaseRequest request){
        return baseService.create(request);
    }

}
