package com.quackaboutit.equipmentapp.tracks.entity;

import java.time.Duration;
import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "arrival_point")
public class ArrivalPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "arrival_point_id_seq")
    @SequenceGenerator(name = "arrival_point_id_seq", sequenceName = "arrival_point_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "planOutTime")
    private LocalDateTime planOutTime;

    @Column(name = "planArrivalTime")
    private LocalDateTime planArrivalTime;

    @Column(name = "planWorkDuration")
    private Duration planWorkDuration;

    @Column(name = "realArrivalTime")
    private LocalDateTime realArrivalTime;

    @Column(name = "realOutTime")
    private LocalDateTime realOutTime;

    @Column(name = "kmOnStart")
    private Double kmOnStart;

    @Column(name = "kmOnEnd")
    private Double kmOnEnd;

    @Column(name = "fuelOnStart")
    private Double fuelOnStart;

    @Column(name = "fuelOnEnd")
    private Double fuelOnEnd;

    @Column(name = "waitTime")
    private LocalDateTime waitTime;
}