package com.quackaboutit.equipmentapp.request.service;

import java.util.ArrayList;
import java.util.List;

import com.quackaboutit.equipmentapp.equipment.dto.RequestedEquipmentResponse;
import com.quackaboutit.equipmentapp.equipment.entity.EquipmentType;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentTypeRepository;
import com.quackaboutit.equipmentapp.request.dto.RequestDetailsResponse;
import com.quackaboutit.equipmentapp.request.dto.RequestedEquipmentUpdateRequest;
import com.quackaboutit.equipmentapp.users.service.JwtService;
import com.quackaboutit.equipmentapp.workplace.entity.Workplace;
import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.equipment.entity.Equipment;
import com.quackaboutit.equipmentapp.equipment.repository.EquipmentRepository;
import com.quackaboutit.equipmentapp.request.dto.RequestForRequest;
import com.quackaboutit.equipmentapp.request.dto.ResponseRequest;
import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.RequestState;
import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;
import com.quackaboutit.equipmentapp.request.repository.RequestEquipmentRepository;
import com.quackaboutit.equipmentapp.request.repository.RequestRepository;
import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.workplace.repository.WorkplaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final WorkplaceRepository workplaceRepository;
    private final JwtService jwtService;
    private final RequestEquipmentRepository requestEquipmentRepository;
    private final EquipmentRepository equipmentRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;

    public List<ResponseRequest> getRequests(Long userId) {
        List<Request> requests = requestRepository.findAllByCreatorId(userId);
        ArrayList<ResponseRequest> responseRequest = new ArrayList<>();
        requests.forEach(request -> responseRequest.add(
                ResponseRequest.fromRequest(request))
        );

        return responseRequest;
    }

    public ResponseRequest postRequest(RequestForRequest request) {
        List<RequestedEquipment> requestedEquipmentList = new ArrayList<>();
        request.getEquipmentInRequest().forEach(eq -> {
            Equipment equipment = equipmentRepository.findById(eq.getEquipmentId()).get();
            EquipmentType equipmentType = equipmentTypeRepository.findById(eq.getEquipmentTypeId()).get();
            for (int i = 0; i < eq.getQuantity(); i++) {
                RequestedEquipment requestedEquipment = RequestedEquipment.builder()
                        .equipment(equipment)
                        .equipmentType(equipmentType)
                        .arrivalTime(eq.getArrivalTime())
                        .workDuration(eq.getWorkDuration())
                        .build();
                requestedEquipment = requestEquipmentRepository.save(requestedEquipment);
                requestedEquipmentList.add(requestedEquipment);
            }
        });

        User sender = jwtService.getUserFromSecurityContextHolder();
        Workplace workplace = workplaceRepository.findById(request.getWorkplaceId()).get();

        Request requestEntity = Request.builder()
                .requestedEquipment(requestedEquipmentList)
                .state(RequestState.SENT)
                .unit(sender.getUnit())
                .workplace(workplace)
                .creator(sender)
                .distance(request.getDistance())
                .arrivalDate(request.getArrivalDate())
                .build();
        requestEntity = requestRepository.save(requestEntity);

        return ResponseRequest.fromRequest(requestEntity);
    }

    public RequestDetailsResponse getRequestDetailById(Long requestId) {
        Request request = requestRepository.findById(requestId).get();
        List<RequestedEquipmentResponse> requestedEquipment = new ArrayList<>();
        request.getRequestedEquipment().forEach(eq -> {
            requestedEquipment.add(RequestedEquipmentResponse.builder()
                    .id(eq.getId())
                    .equipmentId(eq.getEquipment().getId())
                    .equipmentName(eq.getEquipment().getName())
                    .equipmentImage(eq.getEquipment().getImage())
                    .equipmentTypeId(eq.getEquipmentType().getId())
                    .equipmentType(eq.getEquipmentType().getType())
                    .licensePlateNumber(eq.getLicensePlateNumber())
                    .arrivalTime(eq.getArrivalTime().toString())
                    .workDuration(eq.getWorkDuration().toString())
                    .build());
        });

        return RequestDetailsResponse.builder()
                .id(request.getId())
                .state(request.getState())
                .workerName(request.getCreator().getUsername())
                .unitAddress(request.getUnit().getAddress())
                .workplaceAddress(request.getWorkplace().getAddress())
                .distance(request.getDistance())
                .date(request.getArrivalDate().toString())
                .progress(0)
                .total(1)
                .equipment(requestedEquipment)
                .build();
    }

    public void updateRequestedEquipmentById(RequestedEquipmentUpdateRequest request, Long id) {
        requestEquipmentRepository.updateById(id, request.getEquipmentTypeId(),
                request.getLicensePlateNumber(), request.getArrivalTime(), request.getWorkDuration());
    }
}
