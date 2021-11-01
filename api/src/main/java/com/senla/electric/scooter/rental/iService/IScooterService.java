package com.senla.finalProject.iService;

import com.senla.finalProject.dto.ScooterDto;

import java.util.List;

public interface IScooterService {

    ScooterDto save(ScooterDto scooter);

    ScooterDto update(Long id, ScooterDto scooter);

    ScooterDto delete(Long scooterId);

    List<ScooterDto> getAll();
}
