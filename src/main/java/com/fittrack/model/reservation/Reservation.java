package com.fittrack.model.reservation;

import com.fittrack.model.attendance.Attendance;
import com.fittrack.model.classes.Classes;
import com.fittrack.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="fittrack_reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="class_id",nullable = false)
    private Classes classes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status=ReservationStatus.RESERVED;

    @Column(name="reserved_at",nullable = false)
    private LocalDateTime reservedAt=LocalDateTime.now();

    @OneToOne(mappedBy = "reservation")
    private Attendance attendance;
}
