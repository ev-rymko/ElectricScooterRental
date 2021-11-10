package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.LoginDto;
import com.senla.electric.scooter.rental.dto.RentDto;
import com.senla.electric.scooter.rental.dto.RentForHourDto;
import com.senla.electric.scooter.rental.dto.SubscriptionRentDto;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.exceptions.InvalidPriceException;
import com.senla.electric.scooter.rental.iDao.IRentDao;
import com.senla.electric.scooter.rental.iService.IAccountService;
import com.senla.electric.scooter.rental.iService.IScooterPriceService;
import com.senla.electric.scooter.rental.model.*;
import com.senla.electric.scooter.rental.iService.IRentService;
import com.senla.electric.scooter.rental.state.State;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RentService implements IRentService {

    private static final String RENT_NOT_FOUND_EXCEPTION = "Rent not found. ";
    private static final String INVALID_PRICE_EXCEPTION = "The price must be greater than or equal to zero. ";
    private static final String EMPTY_HISTORY_EXCEPTION = "The rental history is empty.";
    private final IRentDao rentDao;
    private final IAccountService accountService;
    private final IScooterPriceService scooterPriceService;
    private final ModelMapper mapper;
    private final State unavailableState;
    @Autowired
    @Qualifier("available")
    private State availableState;

    @Override
    public RentDto addForHour(RentForHourDto rent) {
        Rent resultRent = new Rent.Builder().withRentDate(LocalDateTime.now())
                .withAccount(mapper.map(rent.getAccount(), Account.class))
                .withRentalPoint(mapper.map(rent.getRentalPoint(), RentalPoint.class))
                .withScooter(mapper.map(rent.getScooter(), Scooter.class))
                .withHours(rent.getHours())
                .withPrice(countFinalPrice(mapper.map(rent, RentDto.class)))
                .build();
        Rent savedRent = rentDao.save(resultRent);
        unavailableState.changeState(savedRent);
        return mapper.map(savedRent, RentDto.class);
    }

    @Override
    public RentDto addSubscription(SubscriptionRentDto rent) {
        Rent resultRent = new Rent.Builder().withRentDate(LocalDateTime.now())
                .withAccount(mapper.map(rent.getAccount(), Account.class))
                .withRentalPoint(mapper.map(rent.getRentalPoint(), RentalPoint.class))
                .withScooter(mapper.map(rent.getScooter(), Scooter.class))
                .withSubscription(rent.getSubscription())
                .withPrice(countFinalPrice(mapper.map(rent, RentDto.class)))
                .build();
        Rent savedRent = rentDao.save(resultRent);
        unavailableState.changeState(savedRent);
        return mapper.map(savedRent, RentDto.class);
    }

    @Override
    public RentDto setMileage(Long id, double mileage) {
        Rent rentById = checkAndGetRentById(id);
        rentById.setMileage(mileage);
        Rent resultRent = rentDao.update(id, rentById);
        return mapper.map(resultRent, RentDto.class);
    }

    @Override
    public RentDto update(Long id, RentDto rent) {
        Rent rentById = checkAndGetRentById(id);
        rent.setRentDate(rentById.getRentDate());
        rent.setFinalPrice(countFinalPrice(rent));
        Rent resultRent = rentDao.update(id, mapper.map(rent, Rent.class));
        return mapper.map(resultRent, RentDto.class);
    }

    @Override
    public List<RentDto> getAll() {
        return rentDao.getAll().stream()
                .map(rent -> mapper.map(rent, RentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RentDto> getHistoryForAdministrator(Long scooterId) {
        List<RentDto> rentals = rentDao.getRentalHistoryForAdministrator(scooterId).stream()
                .map(rent -> mapper.map(rent, RentDto.class))
                .collect(Collectors.toList());
        if (rentals.size() == 0) {
            throw new DataNotFoundException(EMPTY_HISTORY_EXCEPTION);
        }
        return rentals;
    }

    @Override
    public List<RentDto> getHistoryForClient(LoginDto dto) {
        List<RentDto> rentals = rentDao.getRentalHistoryForClient
                        (mapper.map(accountService.getUserByLogin(dto.getLogin()), Account.class))
                .stream()
                .map(rent -> mapper.map(rent, RentDto.class))
                .collect(Collectors.toList());
        if (rentals.size() == 0) {
            throw new DataNotFoundException(EMPTY_HISTORY_EXCEPTION);
        }
        return rentals;
    }

    @Override
    public RentDto setPrice(Long rentId, double newPrice) {
        checkPrice(newPrice);
        Rent rent = checkAndGetRentById(rentId);
        rent.setFinalPrice(newPrice);
        Rent updatedRent = rentDao.update(rentId, rent);
        return mapper.map(updatedRent, RentDto.class);
    }

    @Override
    public RentDto setDiscount(Long rentId, int percent) {
        Rent rent = checkAndGetRentById(rentId);
        double newPrice = (rent.getFinalPrice() * percent) / 100;
        checkPrice(newPrice);
        rent.setFinalPrice(newPrice);
        Rent updatedRent = rentDao.update(rentId, rent);
        return mapper.map(updatedRent, RentDto.class);
    }

    private Double countFinalPrice(RentDto rent) {
        ScooterPrice scooterPrice = mapper.map(
                scooterPriceService.findByName(rent.getScooter().getScooterPrice().getScooterType()),
                ScooterPrice.class);
        double finalPrice = 0;
        if (rent.getHours() != 0) {
            finalPrice = scooterPrice.getPricePerHour() * rent.getHours();
            return finalPrice;
        } else if (rent.getSubscription() != null) {
            switch (rent.getSubscription()) {
                case DAY:
                    finalPrice = scooterPrice.getSubscriptionPricePerDay();
                    return finalPrice;
                case WEEK:
                    finalPrice = scooterPrice.getSubscriptionPricePerDay() * 7;
                    return finalPrice;
                case TWO_WEEK:
                    finalPrice = scooterPrice.getSubscriptionPricePerDay() * 14;
                    return finalPrice;
                case MONTH:
                    finalPrice = scooterPrice.getSubscriptionPricePerDay() * 30;
                    return finalPrice;
            }
        }
        return finalPrice;
    }

    @Override
    public RentDto finishTrip(Long id){
        Rent rent = checkAndGetRentById(id);
        availableState.changeState(rent);
        return mapper.map(rent, RentDto.class);
    }

    private Rent checkAndGetRentById(Long id) {
        Rent rent = rentDao.getById(id);
        if (rent == null) {
            throw new DataNotFoundException(RENT_NOT_FOUND_EXCEPTION);
        } else {
            return rent;
        }
    }

    private void checkPrice(double price) {
        if (price < 0) {
            throw new InvalidPriceException(INVALID_PRICE_EXCEPTION);
        }
    }
}
