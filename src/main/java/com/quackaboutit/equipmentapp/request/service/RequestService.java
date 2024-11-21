package com.quackaboutit.equipmentapp.request.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.quackaboutit.equipmentapp.Workplace.repository.WorkplaceRepository;
import com.quackaboutit.equipmentapp.equipment.entity.Equipment;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentRepository;
import com.quackaboutit.equipmentapp.request.dto.EquipmentInRequest;
import com.quackaboutit.equipmentapp.request.dto.RequestForRequest;
import com.quackaboutit.equipmentapp.request.dto.ResponseRequest;
import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.RequestState;
import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;
import com.quackaboutit.equipmentapp.request.repository.RequestEquipmentRepository;
import com.quackaboutit.equipmentapp.request.repository.RequestRepository;
import com.quackaboutit.equipmentapp.users.entity.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final WorkplaceRepository workplaceRepository;
    private final RequestEquipmentRepository requestEquipmentRepository;
    private final EquipmentRepository equipmentRepository;
    
    public List<ResponseRequest> getRequests(Long userId){
        List<Request> requests = requestRepository.findAllByCreatorId(userId);
        ArrayList<ResponseRequest> responseRequest = new ArrayList<>();
        requests.forEach(request -> responseRequest.add(
            ResponseRequest.fromRequest(request)));

        return responseRequest;
    }

    public ResponseRequest postRequest(RequestForRequest req, User curUser){
        List<RequestedEquipment> requestedEquipment = new ArrayList<>(); 
        req.getEquipmentInRequest().forEach(eq -> {
            Equipment equipment = equipmentRepository.findById(eq.getEquipmentId()).get();
            var tmp = EquipmentInRequest.fromEquipmentInRequest(eq, equipment);
            
            requestEquipmentRepository.save(tmp);
            requestedEquipment.add(tmp);
        });

        Request request = new Request(null, RequestState.FUTURE, curUser.getUnit(),
        workplaceRepository.findById(req.getWorkplaceId()).get(), curUser,
        null, requestedEquipment, req.getDistance(), req.getArrivalDate()
        );

        return ResponseRequest.fromRequest(request);
    }
}
