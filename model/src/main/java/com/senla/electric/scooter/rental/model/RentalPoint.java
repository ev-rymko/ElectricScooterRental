package com.senla.electric.scooter.rental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rental_points")
@SequenceGenerator(name = "default_gen", sequenceName = "rental_point_seq", allocationSize = 1)
public class RentalPoint extends AbstractEntity {

    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rentalPoint")
    private List<Scooter> scooters;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rentalPoint")
    private List<Rent> rentals;

    public RentalPoint() {
    }
}
