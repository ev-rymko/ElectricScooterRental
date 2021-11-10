package com.senla.electric.scooter.rental.state;

import com.senla.electric.scooter.rental.iService.IScooterService;
import com.senla.electric.scooter.rental.model.Rent;
import com.senla.electric.scooter.rental.model.Scooter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Primary
@Component("unavailable")
public class StateUnavailable implements State{
    private final IScooterService scooterService;

    @Override
    public void changeState(Rent rent) {
        Scooter scooter = rent.getScooter();
        scooter.setRentalPoint(null);

        scooterService.updateRentalPoint(scooter);
    }
}
