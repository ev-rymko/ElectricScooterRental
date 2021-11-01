package com.senla.finalProject.iService;

import com.senla.finalProject.dto.ScooterPriceDto;

import java.util.List;

public interface IScooterPriceService {

    ScooterPriceDto save(ScooterPriceDto dto);

    ScooterPriceDto update(Long id, ScooterPriceDto dto);

    List<ScooterPriceDto> getAll();
}
