package com.senla.finalProject.dto;

import com.senla.finalProject.enums.ScooterType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScooterPriceDto {

    private ScooterType scooterType;
    private double pricePerHour;
    private double subscriptionPricePerDay;
}
