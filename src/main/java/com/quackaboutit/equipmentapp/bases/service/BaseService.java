package com.quackaboutit.equipmentapp.bases.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.bases.repository.BaseRepository;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.unit.exceptions.UnitNotFound;
import com.quackaboutit.equipmentapp.unit.repository.UnitRepository;
import com.quackaboutit.equipmentapp.bases.dto.BaseRequest;
import com.quackaboutit.equipmentapp.bases.dto.BaseResponse;
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
        bases.forEach(base ->{
            baseResponses.add(BaseResponse.fromBaseToResponse(base));
        });

        return baseResponses;
    }

    public BaseResponse findBaseById(Long id) throws BaseNotFound{
        Optional<Base> base = baseRepository.findById(id);
        if(base.isPresent())
            return BaseResponse.fromBaseToResponse(base.get());
        
        throw new BaseNotFound();
    }
    public BaseResponse create(BaseRequest req) throws UnitNotFound {
        Optional<Unit> unit = unitRepository.findById(req.getUnitId());
        if(unit.isPresent())
            return BaseResponse.fromBaseToResponse(baseRepository.save(new Base(
                null, unit.get(), req.getAddress(), req.getLatitude(), req.getLongitude()
            )));
        throw new UnitNotFound();
    }
}
