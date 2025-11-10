package com.fittrack.model.user;

import com.fittrack.model.attendance.Attendance;
import com.fittrack.model.coach.CoachProfile;
import com.fittrack.model.location.Location;
import com.fittrack.model.payment.Payment;
import com.fittrack.model.reservation.Reservation;
import com.fittrack.model.review.Review;
import com.fittrack.model.subscription.Subscription;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "fittrack_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="password_hash",nullable = false)
    private String password;

    @Column(name="first_name",nullable = false)
    private String firstName;

    @Column(name="last_name",nullable = false)
    private String lastName;

    @Column(name="phone",nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role=UserRole.MEMBER;

    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    @Column(name="updated_at")
    private LocalDateTime updatedAt=LocalDateTime.now();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Subscription> subscription= new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Review> review=new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Reservation> reservations=new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Payment> payments=new HashSet<>();

    @OneToMany(mappedBy = "manager",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Location> location=new HashSet<>();


    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private CoachProfile coachProfile;


    @OneToMany(mappedBy = "markedBy",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Attendance> attendances=new HashSet<>();

    @PreUpdate
    public void onUpdate(){
        this.updatedAt=LocalDateTime.now();
    }



}
