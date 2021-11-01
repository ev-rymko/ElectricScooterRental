package com.senla.finalProject.iService;

import com.senla.finalProject.dto.RentalPointDto;
import com.senla.finalProject.dto.ScooterDto;

import java.util.List;

public interface IRentalPointService {

    RentalPointDto save(RentalPointDto rentalPoint);

    RentalPointDto update(Long id, RentalPointDto rentalPoint);

    RentalPointDto delete(Long rentalPointId);

    List<RentalPointDto> getAllInCountry(String country);

    List<RentalPointDto> getAllInCity(String city);

    List<ScooterDto> getDetails(Long id);

    List<RentalPointDto> getAll();
}
