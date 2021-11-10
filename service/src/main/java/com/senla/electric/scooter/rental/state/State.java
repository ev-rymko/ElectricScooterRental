package com.senla.electric.scooter.rental.state;

import com.senla.electric.scooter.rental.model.Rent;

public interface State {

    void changeState(Rent rent);
}
