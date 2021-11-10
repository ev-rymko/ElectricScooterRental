package com.senla.electric.scooter.rental.iService;

import com.senla.electric.scooter.rental.dto.ScooterDto;
import com.senla.electric.scooter.rental.model.Scooter;

import java.util.List;

public interface IScooterService {

    ScooterDto save(ScooterDto scooter);

    ScooterDto update(Long id, ScooterDto scooter);

    ScooterDto delete(Long scooterId);

    List<ScooterDto> getAll();

    void updateRentalPoint(Scooter scooter);
}
