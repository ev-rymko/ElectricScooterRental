package com.senla.finalProject;

import com.senla.finalProject.dto.ScooterPriceDto;
import com.senla.finalProject.exceptions.DataNotFoundException;
import com.senla.finalProject.exceptions.InvalidPriceException;
import com.senla.finalProject.exceptions.PermissionDeniedException;
import com.senla.finalProject.iDao.IScooterPriceDao;
import com.senla.finalProject.iService.IScooterPriceService;
import com.senla.finalProject.model.ScooterPrice;
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

    private static final String FAILED_SAVE_EXCEPTION = "Scooter price was not save.";
    private static final String SCOOTER_PRICE_NOT_FOUND_EXCEPTION = "Scooter price was not found by this id.";
    private static final String INVALID_PRICE_EXCEPTION = "The price must be greater than or equal to zero. ";
    private final IScooterPriceDao scooterPriceDao;
    private final ModelMapper mapper;

    @Override
    public ScooterPriceDto save(ScooterPriceDto dto) {
        if(dto.getPricePerHour() < 0 && dto.getSubscriptionPricePerDay() < 0){
            throw new InvalidPriceException(INVALID_PRICE_EXCEPTION);
        }
        ScooterPrice scooterPrice = scooterPriceDao.save(mapper.map(dto, ScooterPrice.class));
        if(scooterPrice == null){
            throw new PermissionDeniedException(FAILED_SAVE_EXCEPTION);
        }
        return mapper.map(scooterPrice, ScooterPriceDto.class);
    }

    @Override
    public ScooterPriceDto update(Long id, ScooterPriceDto dto) {
        if (scooterPriceDao.getById(id) == null) {
            throw new DataNotFoundException(SCOOTER_PRICE_NOT_FOUND_EXCEPTION);
        }
        ScooterPrice scooterPrice = scooterPriceDao.update(id, mapper.map(dto, ScooterPrice.class));
        return mapper.map(scooterPrice, ScooterPriceDto.class);
    }

    @Override
    public List<ScooterPriceDto> getAll() {
        return scooterPriceDao.getAll().stream()
                .map(scooterPrice -> mapper.map(scooterPrice, ScooterPriceDto.class))
                .collect(Collectors.toList());
    }
}
