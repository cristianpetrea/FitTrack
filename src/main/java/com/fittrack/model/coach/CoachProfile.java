package com.fittrack.model.coach;

import com.fittrack.model.classes.Classes;
import com.fittrack.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="fittrack_coach_profiles")
public class CoachProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne
    @JoinColumn(name="user_id",unique = true)
    private User user;

    @Column(nullable = false)
    private String bio;

    @Column(nullable = false)
    private String specialties;

    @Column(name="rating_avg",nullable = false)
    private double ratingAvg;

    @OneToMany(mappedBy = "coachProfile",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Classes> classes=new HashSet<>();

}
