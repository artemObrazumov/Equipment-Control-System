package com.quackaboutit.equipmentapp.tracks.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quackaboutit.equipmentapp.tracks.entity.ArrivalPoint;
import com.quackaboutit.equipmentapp.tracks.entity.Track;

import org.springframework.stereotype.Service;

import com.quackaboutit.equipmentapp.equipment.dto.NamedEquipmentResponse;
import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;
import com.quackaboutit.equipmentapp.equipment.repository.NamedEquipmentRepository;
import com.quackaboutit.equipmentapp.request.entity.Request;
import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;
import com.quackaboutit.equipmentapp.request.entity.Summary;
import com.quackaboutit.equipmentapp.request.entity.SummaryState;
import com.quackaboutit.equipmentapp.request.exceptions.SummaryHasClosed;
import com.quackaboutit.equipmentapp.request.exceptions.SummaryNotFound;
import com.quackaboutit.equipmentapp.request.repository.SummaryRepository;
import com.quackaboutit.equipmentapp.tracks.dto.ArrivalPointRequest;
import com.quackaboutit.equipmentapp.tracks.dto.ArrivalPointResponse;
import com.quackaboutit.equipmentapp.tracks.dto.TrackResponse;
import com.quackaboutit.equipmentapp.tracks.exceptions.TrackIsClosed;
import com.quackaboutit.equipmentapp.tracks.exceptions.TrackNotFound;
import com.quackaboutit.equipmentapp.tracks.repository.ArrivalPointRepository;
import com.quackaboutit.equipmentapp.tracks.repository.TrackRepository;
import com.quackaboutit.equipmentapp.utils.ObjectForTrack;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrackService {
    private final TrackRepository trackRepository;
    private final SummaryRepository summaryRepository;
    private final NamedEquipmentRepository namedEquipmentRepository;
    private final ArrivalPointRepository arrivalPointRepository;
    
    public void create(Long summaryId) throws RuntimeException{
        Summary summary = summaryRepository.findById(summaryId).orElseThrow(
            () -> new SummaryNotFound()
        );

        if(summary.getState() != SummaryState.NEW) throw new SummaryHasClosed();
        summaryRepository.updateState(SummaryState.CLOSED, summaryId);
        // key = namedEquipment.licensePlate
        Map<String, List<ObjectForTrack>> namedEquipmentMap = new HashMap<>();
        List<String> licensePlates = new ArrayList<>();

        List<Request> requests = summary.getRequests();
        requests.forEach(request -> { // summary -> List<RequestedEquipment> -> NamedEquipment
            List<RequestedEquipment> requestedEquipments = request.getRequestedEquipment();
            requestedEquipments.forEach(requestedEquipment ->{
                licensePlates.add(requestedEquipment.getLicensePlateNumber());
                
                if(namedEquipmentMap.get(licensePlates.getLast()) == null){ // берём из словаря элемент под ID
                    List<ObjectForTrack> tmp = new ArrayList<>();
                    tmp.add(new ObjectForTrack(requestedEquipment.getArrivalTime(), 
                            requestedEquipment.getArrivalTime().plusHours(
                                requestedEquipment.getWorkDuration().toHours()), request, requestedEquipment));
                    namedEquipmentMap.put(licensePlates.getLast(), tmp);
                }else{
                    namedEquipmentMap.get(licensePlates.getLast()).add(new ObjectForTrack(requestedEquipment.getArrivalTime(), 
                    requestedEquipment.getArrivalTime().plusHours(
                        requestedEquipment.getWorkDuration().toHours()
                    ), request, requestedEquipment));
                }
            });

        });

        licensePlates.forEach(licensePlate -> {
            NamedEquipment namedEquipment = namedEquipmentRepository.findBylicensePlate(licensePlate);
            List<ArrivalPoint> ArrivalPoins = new ArrayList<>();


            namedEquipmentMap.get(licensePlate).forEach(obj ->{


                ArrivalPoins.add(
                    arrivalPointRepository.save(new ArrivalPoint(
                        null, obj.getRequest().getWorkplace().getAddress(),
                        obj.getRequest().getDistance(), obj.getTimeOut(),
                        obj.getTimeArrive(), obj.getRequestedEquipment().getWorkDuration(),
                        null, null, null, null, 
                        null, null, null
                    ))
                );
            });


            trackRepository.save(new Track(null, summary.getDate(),
            namedEquipment, null, true, ArrivalPoins));
        });

    }

    public TrackResponse getTrackById(Long id){
        Track track = trackRepository.findById(id).
                        orElseThrow(() -> new TrackNotFound());

        List<ArrivalPointResponse> arrivalPointResponses = new ArrayList<>();
        track.getArrivalPoint().forEach(point -> {
            arrivalPointResponses.add(ArrivalPointResponse.builder()
                                                .id(point.getId())
                                                .address(point.getAddress())
                                                .planOutTime(point.getPlanOutTime().toString())
                                                .planArrivalTime(point.getPlanArrivalTime().toString())
                                                .distanse(point.getDistance())
                                                .planWorkDuration(point.getPlanWorkDuration())
                                                .realArrivalTime(point.getRealArrivalTime().toString())
                                                .realOutTime(point.getRealOutTime().toString())
                                                .kmOnStart(point.getKmOnStart())
                                                .kmOnEnd(point.getKmOnEnd())
                                                .fuelOnStart(point.getFuelOnStart())
                                                .fuelOnEnd(point.getFuelOnEnd())
                                                .waitTime(point.getWaitTime().toString())
                                                .build()
            );
        });

        return TrackResponse.builder()
                        .id(id)
                        .date(track.getDate().toString())
                        .namedEquipment(track.getNamedEquipment())
                        .driver(track.getDriver())
                        .isActive(track.getIsActive())
                        .arrivalPoints(arrivalPointResponses)
                        .build();
    }

    public List<ArrivalPointResponse> getAllArrivalPointsByTimeArrival(Long id, Long timestamp){
        Track track = trackRepository.findById(id).orElseThrow(
                            () -> new TrackNotFound());
        List<ArrivalPoint> arrivalPoints = track.getArrivalPoint();
        List<ArrivalPointResponse> arrivalPointResponses = new ArrayList<>();

        arrivalPoints.forEach(arrivalPoint -> {
            if(arrivalPoint.getPlanArrivalTime().
                toInstant(ZoneOffset.UTC).toEpochMilli() >= timestamp && timestamp <= 
                arrivalPoint.getPlanArrivalTime().
                toInstant(ZoneOffset.UTC).toEpochMilli() + 86400000){
                    arrivalPointResponses.add(ArrivalPointResponse.builder()
                                                .address(arrivalPoint.getAddress())
                                                .planArrivalTime(arrivalPoint.getPlanArrivalTime().toString())
                                                .planOutTime(arrivalPoint.getPlanOutTime().toString())
                                                .build());
                }
                
        });

        return arrivalPointResponses;
    }
    
    public void pushRealData(List<ArrivalPointRequest> requests, Long id) throws RuntimeException{
        Track track = trackRepository.findById(id).orElseThrow(
            () -> new TrackNotFound()
        );
        if (track.getIsActive() == false) throw new TrackIsClosed();
        trackRepository.closeTrack(track.getId());
        requests.forEach(request -> {
            arrivalPointRepository.updateTrackByUserData(request.getRealArrivalTime(), request.getRealOutTime(), 
                request.getKmOnStart(), request.getKmOnEnd(), request.getFuelOnStart(), 
                request.getFuelOnEnd(), request.getWaitTime(), request.getId());
        });
    }
}
