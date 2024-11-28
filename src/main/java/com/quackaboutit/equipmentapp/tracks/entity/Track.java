package com.quackaboutit.equipmentapp.tracks.entity;

import java.time.LocalDateTime;
import java.util.List;
import com.quackaboutit.equipmentapp.equipment.entity.NamedEquipment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "track")
public class Track { // Трек = одна машина
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "track_id_seq")
    @SequenceGenerator(name = "track_id_seq", sequenceName = "track_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name = "named_equipment_id", referencedColumnName = "id")
    private NamedEquipment namedEquipment;

    @Column(name = "driver")
    private String driver;

    @Column(name = "isActive")
    private Boolean isActive;

    @OneToMany
    @JoinColumn(name = "arrival_point_id", referencedColumnName = "id")
    private List<ArrivalPoint> arrivalPoint;
}
