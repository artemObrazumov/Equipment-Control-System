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

    @Column(name = "unique_name")
    private String uniqueName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_id", referencedColumnName = "id")
    private Base base;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private Equipment equipment;
}
