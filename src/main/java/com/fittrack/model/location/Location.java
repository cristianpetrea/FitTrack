package com.fittrack.model.location;

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
@Table(name="fittrack_locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(name="phone",nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String amenities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private User manager;


    @OneToMany(mappedBy = "location",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Classes> classes=new HashSet<>();

}
