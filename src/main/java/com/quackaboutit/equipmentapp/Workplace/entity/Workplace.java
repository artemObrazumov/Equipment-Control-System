package com.quackaboutit.equipmentapp.workplace.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "workplaces")
public class Workplace {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workplace_id_seq")
    @SequenceGenerator(name = "workplace_id_seq", sequenceName = "workplace_id_seq", allocationSize = 1)
    private Long id;
    
    @Column(name = "state")
    private WorkplaceState state;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "address")
    private String address;
}