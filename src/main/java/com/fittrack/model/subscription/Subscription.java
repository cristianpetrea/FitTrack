package com.fittrack.model.subscription;


import com.fittrack.model.payment.Payment;
import com.fittrack.model.plan.SubscriptionPlan;
import com.fittrack.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="fittrack_subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="plan_id",nullable = false)
    private SubscriptionPlan subPlan;

    @OneToOne
    @JoinColumn(name="payment_id")
    private Payment payment;

    @Column(name="start_date",nullable = false)
    private Date startDate;

    @Column(name="end_date",nullable = false)
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionStatus status = SubscriptionStatus.ACTIVE;



}
