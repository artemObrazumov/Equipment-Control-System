package com.quackaboutit.equipmentapp.request.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requested_equipment")
public class RequestedEquipment {
}
