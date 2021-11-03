package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.RentalPointDto;
import com.senla.electric.scooter.rental.dto.ScooterDto;
import com.senla.electric.scooter.rental.dto.ScooterPriceDto;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.model.RentalPoint;
import com.senla.electric.scooter.rental.model.Scooter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class RentalPointServiceTest {

    @InjectMocks
    RentalPointService rentalPointService;
    @Mock
    ModelMapper mapper;
    @Mock
    RentalPointDao rentalPointDao;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTest() {
        RentalPointDto dto = new RentalPointDto(
                1L, "Belarus", "Grodno", "Sovetskaya square");
        RentalPoint rentalPoint = new RentalPoint();
        rentalPoint.setId(1L);
        rentalPoint.setCountry("Belarus");
        rentalPoint.setCity("Grodno");
        rentalPoint.setAddress("Sovetskaya square");

        Mockito.when(mapper.map(dto, RentalPoint.class)).thenReturn(rentalPoint);
        Mockito.when(mapper.map(rentalPoint, RentalPointDto.class)).thenReturn(dto);

        Mockito.when(rentalPointDao.save(any())).thenReturn(rentalPoint);

        RentalPointDto savedDto = rentalPointService.save(dto);

        assertNotNull(savedDto);
        assertEquals(1L, savedDto.getId());
    }

    @Test
    void updateTest() {
        RentalPointDto dto = new RentalPointDto(
                1L, "Belarus", "Grodno", "Sovetskaya square");
        RentalPoint rentalPoint = new RentalPoint();
        rentalPoint.setId(1L);
        rentalPoint.setCountry("Belarus");
        rentalPoint.setCity("Grodno");
        rentalPoint.setAddress("Sovetskaya square");

        Mockito.when(mapper.map(dto, RentalPoint.class)).thenReturn(rentalPoint);
        Mockito.when(mapper.map(rentalPoint, RentalPointDto.class)).thenReturn(dto);

        Mockito.when(rentalPointDao.getById(1L)).thenReturn(rentalPoint);
        Mockito.when(rentalPointDao.update(1L, rentalPoint)).thenReturn(rentalPoint);

        RentalPointDto updatedRentalPoint = rentalPointService.update(dto.getId(), dto);

        assertNotNull(updatedRentalPoint);
        assertEquals(1L, updatedRentalPoint.getId());
    }

    @Test
    void updateWithExceptionTest() {
        RentalPointDto dto = new RentalPointDto(
                1L, "Belarus", "Grodno", "Sovetskaya square");

        Mockito.when(rentalPointDao.getById(1L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> rentalPointService.update(dto.getId(), dto));
    }

    @Test
    void deleteTest() {
        RentalPointDto dto = new RentalPointDto(
                1L, "Belarus", "Grodno", "Sovetskaya square");
        RentalPoint rentalPoint = new RentalPoint();
        rentalPoint.setId(1L);
        rentalPoint.setCountry("Belarus");
        rentalPoint.setCity("Grodno");
        rentalPoint.setAddress("Sovetskaya square");

        Mockito.when(mapper.map(dto, RentalPoint.class)).thenReturn(rentalPoint);
        Mockito.when(mapper.map(rentalPoint, RentalPointDto.class)).thenReturn(dto);

        Mockito.when(rentalPointDao.getById(1L)).thenReturn(rentalPoint);
        Mockito.when(rentalPointDao.delete(rentalPoint)).thenReturn(rentalPoint);

        RentalPointDto deletedRentalPoint = rentalPointService.delete(dto.getId());

        assertNotNull(deletedRentalPoint);
        assertEquals(1L, deletedRentalPoint.getId());
    }

    @Test
    void deleteWithExceptionTest() {
        RentalPointDto dto = new RentalPointDto(
                1L, "Belarus", "Grodno", "Sovetskaya square");

        Mockito.when(rentalPointDao.getById(1L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> rentalPointService.delete(dto.getId()));
    }

    @Test
    void getAllTest() {
        RentalPointDto dto = new RentalPointDto(
                1L, "Belarus", "Grodno", "Sovetskaya square");
        RentalPointDto dto1 = new RentalPointDto(
                2L, "Belarus", "Minsk", "Sovetskaya square");

        RentalPoint rentalPoint = new RentalPoint();
        rentalPoint.setId(1L);
        rentalPoint.setCountry("Belarus");
        rentalPoint.setCity("Grodno");
        rentalPoint.setAddress("Sovetskaya square");

        RentalPoint rentalPoint1 = new RentalPoint();
        rentalPoint.setId(2L);
        rentalPoint.setCountry("Belarus");
        rentalPoint.setCity("Minsk");
        rentalPoint.setAddress("Sovetskaya square");

        Mockito.when(rentalPointDao.getAll()).thenReturn(Arrays.asList(rentalPoint, rentalPoint1));
        Mockito.when(mapper.map(rentalPoint, RentalPointDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(rentalPoint1, RentalPointDto.class)).thenReturn(dto1);

        List<RentalPointDto> rentalPointList = rentalPointService.getAll();

        assertNotNull(rentalPointList);
        assertEquals(2, rentalPointList.size());
    }

    @Test
    void getAllInCountryTest() {
        RentalPointDto dto = new RentalPointDto(
                1L, "Belarus", "Grodno", "Sovetskaya square");
        RentalPointDto dto1 = new RentalPointDto(
                2L, "Belarus", "Minsk", "Sovetskaya square");

        RentalPoint rentalPoint = new RentalPoint();
        rentalPoint.setId(1L);
        rentalPoint.setCountry("Belarus");
        rentalPoint.setCity("Grodno");
        rentalPoint.setAddress("Sovetskaya square");

        RentalPoint rentalPoint1 = new RentalPoint();
        rentalPoint.setId(2L);
        rentalPoint.setCountry("Belarus");
        rentalPoint.setCity("Minsk");
        rentalPoint.setAddress("Sovetskaya square");

        Mockito.when(rentalPointDao.getAllInCountry("Belarus")).thenReturn(Arrays.asList(rentalPoint, rentalPoint1));
        Mockito.when(mapper.map(rentalPoint, RentalPointDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(rentalPoint1, RentalPointDto.class)).thenReturn(dto1);

        List<RentalPointDto> rentalPointList = rentalPointService.getAllInCountry("Belarus");

        assertNotNull(rentalPointList);
        assertEquals(2, rentalPointList.size());
    }

    @Test
    void getAllInCityTest() {
        RentalPointDto dto = new RentalPointDto(
                1L, "Belarus", "Minsk", "Sovetskaya square");
        RentalPointDto dto1 = new RentalPointDto(
                2L, "Belarus", "Minsk", "Sovetskaya square");

        RentalPoint rentalPoint = new RentalPoint();
        rentalPoint.setId(1L);
        rentalPoint.setCountry("Belarus");
        rentalPoint.setCity("Minsk");
        rentalPoint.setAddress("Sovetskaya square");

        RentalPoint rentalPoint1 = new RentalPoint();
        rentalPoint.setId(2L);
        rentalPoint.setCountry("Belarus");
        rentalPoint.setCity("Minsk");
        rentalPoint.setAddress("Sovetskaya square");

        Mockito.when(rentalPointDao.getAllInCity("Minsk")).thenReturn(Arrays.asList(rentalPoint, rentalPoint1));
        Mockito.when(mapper.map(rentalPoint, RentalPointDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(rentalPoint1, RentalPointDto.class)).thenReturn(dto1);

        List<RentalPointDto> rentalPointList = rentalPointService.getAllInCity("Minsk");

        assertNotNull(rentalPointList);
        assertEquals(2, rentalPointList.size());
    }

    @Test
    void getDetailsTest() {
        ScooterDto dto = new ScooterDto(
                1L, new ScooterPriceDto(), "model", "details", 5, new RentalPointDto());
        ScooterDto dto1 = new ScooterDto(
                2L, new ScooterPriceDto(), "model", "details", 5, new RentalPointDto());
        Scooter scooter = new Scooter();
        Scooter scooter1 = new Scooter();

        Mockito.when(rentalPointDao.getDetails(1L)).thenReturn(Arrays.asList(scooter, scooter1));
        Mockito.when(rentalPointDao.getById(1L)).thenReturn(new RentalPoint());
        Mockito.when(mapper.map(scooter, ScooterDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(scooter1, ScooterDto.class)).thenReturn(dto1);

        List<ScooterDto> scooters = rentalPointService.getDetails(1L);

        assertNotNull(scooters);
        assertEquals(2, scooters.size());
    }

    @Test
    void getDetailsWithExceptionTest() {

        Mockito.when(rentalPointDao.getById(1L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> rentalPointService.getDetails(1L));
    }

}
