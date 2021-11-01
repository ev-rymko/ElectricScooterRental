package com.senla.finalProject;

import com.senla.finalProject.dto.LoginDto;
import com.senla.finalProject.dto.RentDto;
import com.senla.finalProject.exceptions.DataNotFoundException;
import com.senla.finalProject.exceptions.InvalidPriceException;
import com.senla.finalProject.exceptions.PermissionDeniedException;
import com.senla.finalProject.iDao.IAccountDao;
import com.senla.finalProject.iDao.IRentDao;
import com.senla.finalProject.iDao.IScooterPriceDao;
import com.senla.finalProject.iService.IRentService;
import com.senla.finalProject.model.Rent;
import com.senla.finalProject.model.ScooterPrice;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private static final String FAILED_SAVE_EXCEPTION = "Rent was not save";
    private final IRentDao rentDao;
    private final IAccountDao accountDao;
    private final IScooterPriceDao scooterPriceDao;
    private final ModelMapper mapper;

    @Override
    public RentDto save(RentDto rent) {
        Double finalPrice = countFinalPrice(rent);
        rent.setFinalPrice(finalPrice);
        rent.setRentDate(LocalDateTime.now());
        Rent resultRent = rentDao.save(mapper.map(rent, Rent.class));
        if(resultRent == null){
            throw new PermissionDeniedException(FAILED_SAVE_EXCEPTION);
        }
        return mapper.map(resultRent, RentDto.class);
    }

    @Override
    public RentDto update(Long id, RentDto rent) {
        Rent rentById = rentDao.getById(id);
        if (rentById == null) {
            throw new DataNotFoundException(RENT_NOT_FOUND_EXCEPTION);
        }
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
    public List<RentDto> getRentalHistoryForAdministrator(Long scooterId) {
        return rentDao.getRentalHistoryForAdministrator(scooterId).stream()
                .map(rent -> mapper.map(rent, RentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RentDto> getRentalHistoryForClient(LoginDto dto) {
        return rentDao.getRentalHistoryForClient(accountDao.getUserByLogin(dto.getLogin())).stream()
                .map(rent -> mapper.map(rent, RentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RentDto setPrice(Long rentId, double newPrice) {
        if (newPrice < 0) {
            throw new InvalidPriceException(INVALID_PRICE_EXCEPTION);
        }
        Rent rent = rentDao.getById(rentId);
        if (rent == null) {
            throw new DataNotFoundException(RENT_NOT_FOUND_EXCEPTION);
        }
        rent.setFinalPrice(newPrice);
        rentDao.update(rentId, rent);
        return mapper.map(rent, RentDto.class);
    }

    @Override
    public RentDto setDiscount(Long rentId, int percent) {
        Rent rent = rentDao.getById(rentId);
        if (rent == null) {
            throw new DataNotFoundException(RENT_NOT_FOUND_EXCEPTION);
        }
        double newPrice = (rent.getFinalPrice() * percent) / 100;
        if (newPrice < 0) {
            throw new InvalidPriceException(INVALID_PRICE_EXCEPTION);
        }
        rent.setFinalPrice(newPrice);
        rentDao.update(rentId, rent);
        return mapper.map(rent, RentDto.class);
    }

    public Double countFinalPrice(RentDto rent){
        ScooterPrice scooterPrice = scooterPriceDao.findByName(rent.getScooter().getType().name());
        double finalPrice;
        if(rent.getHours() != 0){
            finalPrice = scooterPrice.getPricePerHour() * rent.getHours();
            return finalPrice;
        } else if(rent.getSubscription() != null){
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
        return 0.0;
    }
}
