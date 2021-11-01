package com.senla.finalProject;

import com.senla.finalProject.dto.RentalPointDto;
import com.senla.finalProject.dto.ScooterDto;
import com.senla.finalProject.exceptions.DataNotFoundException;
import com.senla.finalProject.iDao.IRentalPointDao;
import com.senla.finalProject.iService.IRentalPointService;
import com.senla.finalProject.model.RentalPoint;
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
        if (rentalPointDao.getById(id) == null) {
            throw new DataNotFoundException(RENTAL_POINT_NOT_FOUND_EXCEPTION);
        }
        RentalPoint resultRentalPoint = rentalPointDao.update(id, mapper.map(rentalPoint, RentalPoint.class));
        return mapper.map(resultRentalPoint, RentalPointDto.class);
    }

    @Override
    public RentalPointDto delete(Long rentalPointId) {
        RentalPoint rentalPointForDelete = rentalPointDao.getById(rentalPointId);
        if (rentalPointForDelete == null) {
            throw new DataNotFoundException(RENTAL_POINT_NOT_FOUND_EXCEPTION);
        }
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
        return rentalPointDao.getAllInCountry(city).stream()
                .map(rentalPoint -> mapper.map(rentalPoint, RentalPointDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScooterDto> getDetails(Long id) {
        if (rentalPointDao.getById(id) == null) {
            throw new DataNotFoundException(RENTAL_POINT_NOT_FOUND_EXCEPTION);
        }
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
}
