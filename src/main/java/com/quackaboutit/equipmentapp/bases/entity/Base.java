package com.quackaboutit.equipmentapp.bases.entity;

import com.quackaboutit.equipmentapp.unit.entity.Unit;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bases")
public class Base {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_id_seq")
    @SequenceGenerator(name = "base_id_seq", sequenceName = "base_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private Unit unit;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}
