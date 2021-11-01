package com.senla.finalProject.model;

import com.senla.finalProject.enums.Subscription;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "rental_history")
@SequenceGenerator(name = "default_gen", sequenceName = "rent_seq", allocationSize = 1)
public class Rent extends AbstractEntity {

    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_point_id", nullable = false)
    private RentalPoint rentalPoint;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scooter_id", nullable = false)
    private Scooter scooter;
    @Column
    @Enumerated(EnumType.STRING)
    private Subscription subscription;
    @Column
    private int hours;
    @Column(name = "final_price", nullable = false)
    private double finalPrice = 0;
    @Column(nullable = false)
    private double mileage = 0;

    public Rent() {
    }
}
