package com.quackaboutit.equipmentapp.notifications.entity;

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
@Table(name = "notifications")
public class Notification {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_seq")
    @SequenceGenerator(name = "notification_id_seq", sequenceName = "notification_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "user_id")
    private User user;

    @Column(name = "send_time")
    private LocalDateTime sendTime;

    @Column(name = "content")
    private String content;
}
