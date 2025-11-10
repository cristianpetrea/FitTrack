package com.fittrack.model.review;

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
@Table(name="fittrack_reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id",nullable = false)
    private User user;

    @Column(name="target_type",nullable = false)
    private ReviewType targetType;

    @Column(name="target_id",nullable = false)
    private UUID targetId;

    @Column(nullable = false)
    private int rating;

    @Column()
    private String comment;

    @Column(name="created_at",updatable = false)
    private LocalDateTime createdAt=LocalDateTime.now();


}
