package com.quackaboutit.equipmentapp.request.entity;

import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.users.entity.User;
import com.quackaboutit.equipmentapp.workplace.entity.Workplace;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "state")
    private RequestState state;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "workplace_id")
    private Workplace workplace;

    @JoinColumn(name = "user_creator_id")
    @ManyToOne
    private User creator;

    @JoinColumn(name = "user_editor_id")
    @ManyToOne
    private User editor;

    @OneToMany
    @JoinColumn(name = "requested_equipment")
    private List<RequestedEquipment> requestedEquipment;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "arrivaldate")
    private LocalDateTime arrivalDate;
}

