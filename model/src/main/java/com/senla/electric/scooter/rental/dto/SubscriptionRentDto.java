package com.senla.electric.scooter.rental.dto;

import com.senla.electric.scooter.rental.enums.Subscription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRentDto {

    private AccountDto account;
    private RentalPointDto rentalPoint;
    private ScooterDto scooter;
    private Subscription subscription;
}
