package com.quackaboutit.equipmentapp.request.entity;

import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.utils.DurationConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requests")
public class Request {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_id_seq")
    @SequenceGenerator(name = "request_id_seq", sequenceName = "request_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_equipment")
    private List<RequestedEquipment> requestedEquipment;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Convert(converter = DurationConverter.class)
    @Column(name = "work_duration")
    private Duration workDuration;

    @Column(name = "distance")
    private Double distance;
}
