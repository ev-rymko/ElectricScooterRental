package com.senla.electric.scooter.rental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "scooter_prices")
@SequenceGenerator(name = "default_gen", sequenceName = "scooter_price_seq", allocationSize = 1)
public class ScooterPrice extends AbstractEntity{

    @Column(name = "scooter_type")
    private String scooterType;
    @Column(name = "price_per_hour")
    private double pricePerHour;
    @Column(name = "subscription_price_per_day")
    private double subscriptionPricePerDay;
    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "scooterPrice")
    List<Scooter> scooters;

    public ScooterPrice() {
    }
}
