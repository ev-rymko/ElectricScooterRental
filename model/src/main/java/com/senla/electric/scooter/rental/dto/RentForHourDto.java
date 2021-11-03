package com.senla.electric.scooter.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentForHourDto {

    private AccountDto account;
    private RentalPointDto rentalPoint;
    private ScooterDto scooter;
    private int hours;
}
