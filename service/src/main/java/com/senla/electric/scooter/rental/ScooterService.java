package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.ScooterDto;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.iDao.IScooterDao;
import com.senla.electric.scooter.rental.iService.IScooterService;
import com.senla.electric.scooter.rental.model.Scooter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ScooterService implements IScooterService {

    private static final String SCOOTER_NOT_FOUND_BY_ID_EXCEPTION = "The scooter with tis id not found. ";
    private final IScooterDao scooterDao;
    private final ModelMapper mapper;

    @Override
    public ScooterDto save(ScooterDto scooter) {
        Scooter resultScooter = scooterDao.save(mapper.map(scooter, Scooter.class));
        return mapper.map(resultScooter, ScooterDto.class);
    }

    @Override
    public ScooterDto update(Long id, ScooterDto scooter) {
        checkAndGetScooterById(id);
        Scooter resultScooter = scooterDao.update(id, mapper.map(scooter, Scooter.class));
        return mapper.map(resultScooter, ScooterDto.class);
    }

    @Override
    public ScooterDto delete(Long scooterId) {
        Scooter scooterForDelete = checkAndGetScooterById(scooterId);
        Scooter resultScooter = scooterDao.delete(scooterForDelete);
        return mapper.map(resultScooter, ScooterDto.class);
    }

    @Override
    public List<ScooterDto> getAll() {
        return scooterDao.getAll().stream()
                .map(scooter -> mapper.map(scooter, ScooterDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateRentalPoint(Scooter scooter){
        scooterDao.update(scooter.getId(), scooter);
    }

    private Scooter checkAndGetScooterById(Long id) {
        Scooter scooter = scooterDao.getById(id);
        if (scooter == null) {
            throw new DataNotFoundException(SCOOTER_NOT_FOUND_BY_ID_EXCEPTION);
        } else {
            return scooter;
        }
    }
}
