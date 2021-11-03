package com.senla.electric.scooter.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScooterDto {

    private Long id;
    private ScooterPriceDto scooterPrice;
    private String model;
    private String details;
    private int condition;
    private RentalPointDto rentalPoint;
}
