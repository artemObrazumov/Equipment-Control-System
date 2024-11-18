package com.quackaboutit.equipmentapp.request.entity;

import com.quackaboutit.equipmentapp.equipment.entity.Equipment;
import com.quackaboutit.equipmentapp.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requested_equipment")
public class RequestedEquipment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requested_equipment_id_seq")
    @SequenceGenerator(name = "requested_equipment_id_seq", sequenceName = "requested_equipment_id_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "quantity")
    private Integer quantity;
}
