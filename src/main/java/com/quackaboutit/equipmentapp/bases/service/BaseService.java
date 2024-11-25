package com.quackaboutit.equipmentapp.bases.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.bases.repository.BaseRepository;
import com.quackaboutit.equipmentapp.unit.dto.UnitResponse;
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
            baseResponses.add(BaseResponse.fromBaseToResponse(base));
            System.out.println(base.getUnit().getId());
        });

        return baseResponses;
    }

    public List<BaseResponse> findByAddressContaining(String substr){
        List<Base> bases = baseRepository.findByAddressContaining(substr);
        List<BaseResponse> baseResponses = new ArrayList<>();
        bases.forEach(base -> {
            baseResponses.add(BaseResponse.fromBaseToResponse(base));
        });

        return baseResponses;
    }

    public BaseResponse findBaseById(Long id) throws BaseNotFound{
        Base base = baseRepository.findById(id).orElseThrow(() -> new BaseNotFound());
        return BaseResponse.fromBaseToResponse(base);
    }
    public BaseResponse create(BaseRequest request) throws UnitNotFound {
        Unit unit = unitRepository.findById(request.getUnitId()).orElseThrow(() -> new UnitNotFound());;
        return BaseResponse.fromBaseToResponse(baseRepository.save(new Base(
            null, unit, request.getAddress(), request.getLatitude(), request.getLongitude()
        )));
    }

    public void update(Long id, BaseUpdateRequest request){
        baseRepository.updateBase(request.getAddress(), 
            request.getLatitude(), request.getLongitude(), id);
    }

    public void delete(Long id){
        baseRepository.deleteById(id);
    }
}
