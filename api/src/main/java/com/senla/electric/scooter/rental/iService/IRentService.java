package com.senla.finalProject.iService;

import com.senla.finalProject.dto.LoginDto;
import com.senla.finalProject.dto.RentDto;

import java.util.List;

public interface IRentService {

    RentDto save(RentDto rent);

    RentDto update(Long id, RentDto rent);

    List<RentDto> getAll();

    List<RentDto> getRentalHistoryForAdministrator(Long scooterId);

    List<RentDto> getRentalHistoryForClient(LoginDto dto);

    RentDto setPrice(Long rentId, double newPrice);

    RentDto setDiscount(Long rentId, int percent);
}
