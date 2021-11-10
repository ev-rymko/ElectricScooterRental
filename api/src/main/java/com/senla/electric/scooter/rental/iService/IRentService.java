package com.senla.electric.scooter.rental.iService;

import com.senla.electric.scooter.rental.dto.LoginDto;
import com.senla.electric.scooter.rental.dto.RentDto;
import com.senla.electric.scooter.rental.dto.RentForHourDto;
import com.senla.electric.scooter.rental.dto.SubscriptionRentDto;

import java.util.List;

public interface IRentService {

    RentDto addForHour(RentForHourDto rent);

    RentDto addSubscription(SubscriptionRentDto rent);

    RentDto update(Long id, RentDto rent);

    List<RentDto> getAll();

    List<RentDto> getHistoryForAdministrator(Long scooterId);

    List<RentDto> getHistoryForClient(LoginDto dto);

    RentDto setPrice(Long rentId, double newPrice);

    RentDto setDiscount(Long rentId, int percent);

    RentDto setMileage(Long id, double mileage);

    RentDto finishTrip(Long id);
}
