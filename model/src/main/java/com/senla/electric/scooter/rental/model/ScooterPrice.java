package com.senla.finalProject.model;

import com.senla.finalProject.enums.ScooterType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "scooter_price")
@SequenceGenerator(name = "default_gen", sequenceName = "scooter_price_seq", allocationSize = 1)
public class ScooterPrice extends AbstractEntity{

    @Column(name = "scooter_type")
    @Enumerated(EnumType.STRING)
    private ScooterType scooterType;
    @Column(name = "price_per_hour")
    private double pricePerHour;
    @Column(name = "subscription_price_per_day")
    private double subscriptionPricePerDay;

    public ScooterPrice() {
    }
}
