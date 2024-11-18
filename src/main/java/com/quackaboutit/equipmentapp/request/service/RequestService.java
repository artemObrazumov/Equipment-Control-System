package com.quackaboutit.equipmentapp.request.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.request.dto.ResponseRequest;
import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.repository.RequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    public List<ResponseRequest> getRequests(Long userId){
        List<Request> requests = requestRepository.findAllByCreatorId(userId);
        ArrayList<ResponseRequest> responseRequest = new ArrayList<>();
        requests.forEach(request -> responseRequest.add(
            ResponseRequest.fromRequest(request)));

        return responseRequest;
    }
}
