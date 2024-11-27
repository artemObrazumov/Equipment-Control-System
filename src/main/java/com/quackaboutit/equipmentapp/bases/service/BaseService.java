package com.quackaboutit.equipmentapp.bases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.bases.repository.BaseRepository;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.unit.exceptions.UnitNotFound;
import com.quackaboutit.equipmentapp.unit.repository.UnitRepository;
import com.quackaboutit.equipmentapp.bases.dto.BaseRequest;
import com.quackaboutit.equipmentapp.bases.dto.BaseResponse;
import com.quackaboutit.equipmentapp.bases.dto.BaseUpdateRequest;
import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.bases.exceptions.BaseNotFound;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BaseService {
    private final BaseRepository baseRepository;
    private final UnitRepository unitRepository; 

    public List<BaseResponse> findBases(){
        List<Base> bases = baseRepository.findAll();
        List<BaseResponse> baseResponses = new ArrayList<>();
        bases.forEach(base -> {
            baseResponses.add(BaseResponse.builder()
                                .id(base.getId())
                                .unit(base.getUnit())   
                                .address(base.getAddress())
                                .latitude(base.getLatitude())
                                .longitude(base.getLongitude())
                                .build() 
            );
            System.out.println(base.getUnit().getId());
        });

        return baseResponses;
    }

    public List<BaseResponse> findByAddressContaining(String substr){
        List<Base> bases = baseRepository.findByAddressContaining(substr);
        List<BaseResponse> baseResponses = new ArrayList<>();
        bases.forEach(base -> {
            baseResponses.add(BaseResponse.builder()
                                .id(base.getId())
                                .unit(base.getUnit())   
                                .address(base.getAddress())
                                .latitude(base.getLatitude())
                                .longitude(base.getLongitude())
                                .build());
        });

        return baseResponses;
    }

    public BaseResponse findBaseById(Long id) throws BaseNotFound{
        Base base = baseRepository.findById(id).orElseThrow(() -> new BaseNotFound());
        return BaseResponse.builder()
                        .id(base.getId())
                        .unit(base.getUnit())   
                        .address(base.getAddress())
                        .latitude(base.getLatitude())
                        .longitude(base.getLongitude())
                        .build();
    }
    public BaseResponse create(BaseRequest request) throws UnitNotFound {
        Unit unit = unitRepository.findById(request.getUnitId()).orElseThrow(() -> new UnitNotFound());;
        var base = baseRepository.save(new Base(
            null, unit, request.getAddress(), request.getLatitude(), request.getLongitude()
        ));
        
        return BaseResponse.builder()
                .id(base.getId())
                .unit(base.getUnit())   
                .address(base.getAddress())
                .latitude(base.getLatitude())
                .longitude(base.getLongitude())
                .build();
    }

    public void update(Long id, BaseUpdateRequest request) throws UnitNotFound{
        Unit unit = unitRepository.findById(request.getUnitId()).orElseThrow(()
            -> new UnitNotFound());

        baseRepository.updateBase(request.getAddress(), 
            request.getLatitude(), request.getLongitude(), unit, id);
    }

    public void delete(Long id){
        baseRepository.deleteById(id);
    }
}
