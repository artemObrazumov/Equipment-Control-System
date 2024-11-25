// package com.quackaboutit.equipmentapp.tracks.entity;

// import java.time.LocalDateTime;

// import com.fasterxml.jackson.databind.deser.Deserializers.Base;
// import com.quackaboutit.equipmentapp.request.entity.RequestedEquipment;
// import com.quackaboutit.equipmentapp.unit.entity.Unit;
// import com.quackaboutit.equipmentapp.workplace.entity.Workplace;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.OneToOne;
// import jakarta.persistence.SequenceGenerator;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Entity
// @Builder
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Table(name = "track")
// public class Track {
//     @Id
//     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "track_id_seq")
//     @SequenceGenerator(name = "track_id_seq", sequenceName = "track_id_seq", allocationSize = 1)
//     private Long id;

//     @OneToOne
//     @JoinColumn(name = "requested_equipment_id", referencedColumnName = "id")
//     private RequestedEquipment requestedEquipments;

//     @Column(name = "driver_name")
//     private String driverName;

//     @Column(name = "car_brand")
//     private String carBrand;

//     @OneToOne
//     @JoinColumn(name = "workplace_id", referencedColumnName = "id")
//     private Workplace workplaces;

//     @OneToOne
//     @JoinColumn(name = "unit_id", referencedColumnName = "id")
//     private Unit unit;

//     @OneToOne
//     @JoinColumn(name = "base_id", referencedColumnName = "id")
//     private Base base;
    
//     @Column(name = "distance")
//     private Double distance;

//     @Column(name = "startTime")
//     private LocalDateTime startTime;

//     @Column(name = "finishTime")
//     private LocalDateTime finishTime;

//     @Column(name = "fact_distance")
//     private Double factDistance;

//     @Column(name = "fact_startTime")
//     private LocalDateTime factStartTime;

//     @Column(name = "fact_finishTime")
//     private LocalDateTime factFinishTime;
// }
