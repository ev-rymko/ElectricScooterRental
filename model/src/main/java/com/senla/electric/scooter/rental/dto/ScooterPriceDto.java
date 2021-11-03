package com.senla.electric.scooter.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScooterPriceDto {

    private Long id;
    private String scooterType;
    private double pricePerHour;
    private double subscriptionPricePerDay;
}
