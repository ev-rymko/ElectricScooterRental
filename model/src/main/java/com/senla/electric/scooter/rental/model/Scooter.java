package com.senla.electric.scooter.rental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "scooters")
@SequenceGenerator(name = "default_gen", sequenceName = "scooter_seq", allocationSize = 1)
public class Scooter extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scooter_price_id", nullable = false)
    private ScooterPrice scooterPrice;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String details;
    @Column(name = "scooter_condition", nullable = false)
    private int condition = 5;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_point_id", nullable = false)
    private RentalPoint rentalPoint;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "scooter")
    private List<Rent> rentList;

    public Scooter() {
    }
}
