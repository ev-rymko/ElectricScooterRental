package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.exceptions.InvalidPriceException;
import com.senla.electric.scooter.rental.dto.ScooterPriceDto;
import com.senla.electric.scooter.rental.iDao.IScooterPriceDao;
import com.senla.electric.scooter.rental.iService.IScooterPriceService;
import com.senla.electric.scooter.rental.model.ScooterPrice;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ScooterPriceService implements IScooterPriceService {

    private static final String SCOOTER_PRICE_NOT_FOUND_EXCEPTION = "Scooter price was not found by this id.";
    private static final String INVALID_PRICE_EXCEPTION = "The price must be greater than or equal to zero. ";
    private final IScooterPriceDao scooterPriceDao;
    private final ModelMapper mapper;

    @Override
    public ScooterPriceDto save(ScooterPriceDto dto) {
        if (dto.getPricePerHour() < 0 && dto.getSubscriptionPricePerDay() < 0) {
            throw new InvalidPriceException(INVALID_PRICE_EXCEPTION);
        }
        ScooterPrice scooterPrice = scooterPriceDao.save(mapper.map(dto, ScooterPrice.class));
        return mapper.map(scooterPrice, ScooterPriceDto.class);
    }

    @Override
    public ScooterPriceDto update(Long id, ScooterPriceDto dto) {
        checkScooterPriceById(id);
        ScooterPrice scooterPrice = scooterPriceDao.update(id, mapper.map(dto, ScooterPrice.class));
        return mapper.map(scooterPrice, ScooterPriceDto.class);
    }

    @Override
    public ScooterPriceDto delete(Long id) {
        ScooterPrice scooterPriceForDelete = checkScooterPriceById(id);
        ScooterPrice resultScooterPrice = scooterPriceDao.delete(scooterPriceForDelete);
        return mapper.map(resultScooterPrice, ScooterPriceDto.class);
    }

    @Override
    public List<ScooterPriceDto> getAll() {
        return scooterPriceDao.getAll().stream()
                .map(scooterPrice -> mapper.map(scooterPrice, ScooterPriceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ScooterPrice findByName(String name) {
        return scooterPriceDao.findByName(name);
    }

    private ScooterPrice checkScooterPriceById(Long id) {
        ScooterPrice scooterPrice = scooterPriceDao.getById(id);
        if (scooterPrice == null) {
            throw new DataNotFoundException(SCOOTER_PRICE_NOT_FOUND_EXCEPTION);
        } else {
            return scooterPrice;
        }
    }
}
