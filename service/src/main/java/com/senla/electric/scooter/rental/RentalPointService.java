package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.RentalPointDto;
import com.senla.electric.scooter.rental.dto.ScooterDto;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.iDao.IRentalPointDao;
import com.senla.electric.scooter.rental.iService.IRentalPointService;
import com.senla.electric.scooter.rental.model.RentalPoint;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RentalPointService implements IRentalPointService {

    private static final String RENTAL_POINT_NOT_FOUND_EXCEPTION = "Rental point with this id not found.";
    private final IRentalPointDao rentalPointDao;
    private final ModelMapper mapper;

    @Override
    public RentalPointDto save(RentalPointDto rentalPoint) {
        RentalPoint resultRentalPoint = rentalPointDao.save(mapper.map(rentalPoint, RentalPoint.class));
        return mapper.map(resultRentalPoint, RentalPointDto.class);
    }

    @Override
    public RentalPointDto update(Long id, RentalPointDto rentalPoint) {
        checkAndGetRentalPointById(id);
        RentalPoint resultRentalPoint = rentalPointDao.update(id, mapper.map(rentalPoint, RentalPoint.class));
        return mapper.map(resultRentalPoint, RentalPointDto.class);
    }

    @Override
    public RentalPointDto delete(Long rentalPointId) {
        RentalPoint rentalPointForDelete = checkAndGetRentalPointById(rentalPointId);
        RentalPoint resultRentalPoint = rentalPointDao.delete(rentalPointForDelete);
        return mapper.map(resultRentalPoint, RentalPointDto.class);
    }

    @Override
    public List<RentalPointDto> getAllInCountry(String country) {
        return rentalPointDao.getAllInCountry(country).stream()
                .map(rentalPoint -> mapper.map(rentalPoint, RentalPointDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalPointDto> getAllInCity(String city) {
        return rentalPointDao.getAllInCity(city).stream()
                .map(rentalPoint -> mapper.map(rentalPoint, RentalPointDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScooterDto> getDetails(Long id) {
        checkAndGetRentalPointById(id);
        return rentalPointDao.getDetails(id).stream()
                .map(scooter -> mapper.map(scooter, ScooterDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RentalPointDto> getAll(){
        return rentalPointDao.getAll().stream()
                .map(rentalPoint -> mapper.map(rentalPoint, RentalPointDto.class))
                .collect(Collectors.toList());
    }

    private RentalPoint checkAndGetRentalPointById(Long id){
        RentalPoint rentalPoint = rentalPointDao.getById(id);
        if (rentalPoint == null) {
            throw new DataNotFoundException(RENTAL_POINT_NOT_FOUND_EXCEPTION);
        } else {
            return rentalPoint;
        }
    }
}
