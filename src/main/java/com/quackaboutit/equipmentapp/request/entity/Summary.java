package com.quackaboutit.equipmentapp.request.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.quackaboutit.equipmentapp.unit.entity.Unit;
import com.quackaboutit.equipmentapp.users.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "summary")
public class Summary  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summary_id_seq")
    @SequenceGenerator(name = "summary_id_seq", sequenceName = "summary_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "state")
    private SummaryState state;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User manager;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private Unit unit;
    
    @OneToMany
    @JoinColumn(name = "requests")
    private List<Request> requests;
}
