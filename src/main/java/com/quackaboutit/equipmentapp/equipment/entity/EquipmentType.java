package com.quackaboutit.equipmentapp.equipment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "equipment_type")
public class EquipmentType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipment_type_id_seq")
    @SequenceGenerator(name = "equipment_type_id_seq", sequenceName = "equipment_type_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private Equipment equipment;
}
