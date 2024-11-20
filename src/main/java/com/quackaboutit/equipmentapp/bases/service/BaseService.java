package com.quackaboutit.equipmentapp.bases.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.bases.repository.BaseRepository;
import com.quackaboutit.equipmentapp.bases.dto.BaseRequest;
import com.quackaboutit.equipmentapp.bases.dto.BaseResponse;
import com.quackaboutit.equipmentapp.bases.entity.Base;
import com.quackaboutit.equipmentapp.bases.exceptions.NotFoundBase;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BaseService {
    private final BaseRepository baseRepository;

    public List<BaseResponse> findBases(){
        List<Base> bases = baseRepository.findAll();
        List<BaseResponse> baseResponses = new ArrayList<>();
        bases.forEach(base ->{
            baseResponses.add(BaseResponse.fromBaseToResponse(base));
        });

        return baseResponses;
    }

    public BaseResponse findBaseById(Long id) throws NotFoundBase{
        Optional<Base> base = baseRepository.findById(id);
        if(base.isPresent())
            return BaseResponse.fromBaseToResponse(base.get());
        
        throw new NotFoundBase("Base with this ID doesn`t exist");
    }

    public BaseResponse create(BaseRequest req){
        return BaseResponse.fromBaseToResponse(baseRepository.save(new Base(
            null, req.getUnit(), req.getAddress(), req.getLatitude(), req.getLongitude()
        )));
    }
}
