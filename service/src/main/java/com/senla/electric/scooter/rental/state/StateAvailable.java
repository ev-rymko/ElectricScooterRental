package com.senla.electric.scooter.rental.state;

import com.senla.electric.scooter.rental.iService.IScooterService;
import com.senla.electric.scooter.rental.model.Rent;
import com.senla.electric.scooter.rental.model.Scooter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("available")
public class StateAvailable implements State{
    private final IScooterService scooterService;

    @Override
    public void changeState(Rent rent) {
        Scooter scooter = rent.getScooter();
        scooter.setRentalPoint(rent.getRentalPoint());

        scooterService.updateRentalPoint(scooter);
    }
}
