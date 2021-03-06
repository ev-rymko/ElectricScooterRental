package com.senla.electric.scooter.rental.iService;

import com.senla.electric.scooter.rental.dto.ScooterPriceDto;

import java.util.List;

public interface IScooterPriceService {

    ScooterPriceDto save(ScooterPriceDto dto);

    ScooterPriceDto update(Long id, ScooterPriceDto dto);

    ScooterPriceDto delete(Long id);

    List<ScooterPriceDto> getAll();

    ScooterPriceDto findByName(String name);

}
