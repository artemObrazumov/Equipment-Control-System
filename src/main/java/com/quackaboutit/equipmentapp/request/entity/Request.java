package com.quackaboutit.equipmentapp.request.entity;

import com.quackaboutit.equipmentapp.Workplace.entity.Workplace;
import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.users.entity.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workplace_id")
    private Workplace workplace;

    @Column(name = "user_asked_id")
    private User userAsked;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_equipment")
    private List<RequestedEquipment> requestedEquipment;

    @Convert(converter = DurationConverter.class)
    @Column(name = "work_duration")
    private Duration workDuration;

    @Column(name = "distance")
    private Double distance;
}
