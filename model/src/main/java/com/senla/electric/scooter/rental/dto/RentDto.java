package com.senla.electric.scooter.rental.dto;

import com.senla.electric.scooter.rental.enums.Subscription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {

    private Long id;
    private LocalDateTime rentDate;
    private AccountDto account;
    private RentalPointDto rentalPoint;
    private ScooterDto scooter;
    private Subscription subscription;
    private int hours;
    private double finalPrice;
    private double mileage;
}
