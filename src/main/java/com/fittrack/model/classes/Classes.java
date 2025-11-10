package com.fittrack.model.classes;

import com.fittrack.model.coach.CoachProfile;
import com.fittrack.model.location.Location;
import com.fittrack.model.reservation.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="fittrack_classes")
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="coach_id",nullable = false)
    private CoachProfile coachProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="location_id",nullable = false)
    private Location location;

    @Column(name="start_time",nullable = false)
    private Timestamp startTime;

    @Column(name="duration_minutes",nullable = false)
    private int durationMin;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy ="classes",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Reservation> reservations=new HashSet<>();

}
