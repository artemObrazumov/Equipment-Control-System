package com.quackaboutit.equipmentapp.equipment.entity;

import com.quackaboutit.equipmentapp.bases.entity.Base;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "named_equipment")
public class NamedEquipment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "named_equipment_id_seq")
    @SequenceGenerator(name = "named_equipment_id_seq", sequenceName = "named_equipment_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;

    @Column(name = "car_brand")
    private String carBrand;

    @ManyToOne
    @JoinColumn(name = "base_id", referencedColumnName = "id")
    private Base base;

    @ManyToOne
    @JoinColumn(name = "equipment_type_id", referencedColumnName = "id")
    private EquipmentType equipmentType;
}
