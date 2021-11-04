package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.ScooterPriceDto;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.exceptions.InvalidPriceException;
import com.senla.electric.scooter.rental.model.ScooterPrice;
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

public class ScooterPriceServiceTest {

    @InjectMocks
    ScooterPriceService scooterPriceService;
    @Mock
    ScooterPriceDao scooterPriceDao;
    @Mock
    ModelMapper mapper;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTest() {
        ScooterPriceDto dto = new ScooterPriceDto();
        dto.setId(1L);
        dto.setPricePerHour(20);
        dto.setSubscriptionPricePerDay(30);
        ScooterPrice scooterPrice = new ScooterPrice();
        scooterPrice.setId(1L);
        scooterPrice.setPricePerHour(20);
        scooterPrice.setSubscriptionPricePerDay(30);

        Mockito.when(scooterPriceDao.save(scooterPrice)).thenReturn(scooterPrice);
        Mockito.when(mapper.map(dto, ScooterPrice.class)).thenReturn(scooterPrice);
        Mockito.when(mapper.map(scooterPrice, ScooterPriceDto.class)).thenReturn(dto);

        ScooterPriceDto savedScooterPrice = scooterPriceService.save(dto);

        assertNotNull(savedScooterPrice);
        assertEquals(1L, savedScooterPrice.getId());
    }

    @Test
    void saveTestWithException() {
        ScooterPriceDto dto = new ScooterPriceDto();
        dto.setId(1L);
        dto.setPricePerHour(-2);
        dto.setSubscriptionPricePerDay(30);

        assertThrows(InvalidPriceException.class, () -> scooterPriceService.save(dto));
    }

    @Test
    void updateTest() {
        ScooterPriceDto dto = new ScooterPriceDto();
        dto.setId(1L);
        dto.setPricePerHour(20);
        dto.setSubscriptionPricePerDay(30);
        ScooterPrice scooterPrice = new ScooterPrice();
        scooterPrice.setId(1L);
        scooterPrice.setPricePerHour(20);
        scooterPrice.setSubscriptionPricePerDay(30);

        Mockito.when(scooterPriceDao.update(1L, scooterPrice)).thenReturn(scooterPrice);
        Mockito.when(scooterPriceDao.getById(1L)).thenReturn(scooterPrice);
        Mockito.when(mapper.map(dto, ScooterPrice.class)).thenReturn(scooterPrice);
        Mockito.when(mapper.map(scooterPrice, ScooterPriceDto.class)).thenReturn(dto);

        ScooterPriceDto updatedScooterPrice = scooterPriceService.update(1L, dto);

        assertNotNull(updatedScooterPrice);
        assertEquals(1L, updatedScooterPrice.getId());
    }

    @Test
    void updateTestWithBadIdException() {
        ScooterPriceDto dto = new ScooterPriceDto();
        dto.setId(1L);
        dto.setPricePerHour(20);
        dto.setSubscriptionPricePerDay(30);
        ScooterPrice scooterPrice = new ScooterPrice();
        scooterPrice.setId(1L);
        scooterPrice.setPricePerHour(20);
        scooterPrice.setSubscriptionPricePerDay(30);

        Mockito.when(scooterPriceDao.getById(1L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> scooterPriceService.update(1L, dto));
    }

    @Test
    void updateTestWithInvalidPriceException() {
        ScooterPriceDto dto = new ScooterPriceDto();
        dto.setId(1L);
        dto.setPricePerHour(-20);
        dto.setSubscriptionPricePerDay(30);

        assertThrows(InvalidPriceException.class, () -> scooterPriceService.update(1L, dto));
    }

    @Test
    void deleteTest() {
        ScooterPriceDto dto = new ScooterPriceDto();
        dto.setId(1L);
        dto.setPricePerHour(20);
        dto.setSubscriptionPricePerDay(30);
        ScooterPrice scooterPrice = new ScooterPrice();
        scooterPrice.setId(1L);
        scooterPrice.setPricePerHour(20);
        scooterPrice.setSubscriptionPricePerDay(30);

        Mockito.when(scooterPriceDao.delete(scooterPrice)).thenReturn(scooterPrice);
        Mockito.when(scooterPriceDao.getById(1L)).thenReturn(scooterPrice);
        Mockito.when(mapper.map(scooterPrice, ScooterPriceDto.class)).thenReturn(dto);

        ScooterPriceDto deletedScooterPrice = scooterPriceService.delete(1L);

        assertNotNull(deletedScooterPrice);
        assertEquals(1L, deletedScooterPrice.getId());
    }

    @Test
    void getAllTest() {
        ScooterPriceDto dto = new ScooterPriceDto();
        dto.setId(1L);
        dto.setPricePerHour(20);
        dto.setSubscriptionPricePerDay(30);
        ScooterPrice scooterPrice = new ScooterPrice();
        scooterPrice.setId(1L);
        scooterPrice.setPricePerHour(20);
        scooterPrice.setSubscriptionPricePerDay(30);
        ScooterPrice scooterPrice1 = new ScooterPrice();
        scooterPrice1.setId(2L);

        Mockito.when(mapper.map(scooterPrice, ScooterPriceDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(scooterPrice1, ScooterPriceDto.class)).thenReturn(dto);
        Mockito.when(scooterPriceDao.getAll()).thenReturn(Arrays.asList(scooterPrice, scooterPrice1));

        List<ScooterPriceDto> scooterPrices = scooterPriceService.getAll();

        assertNotNull(scooterPrices);
        assertEquals(2, scooterPrices.size());
    }
}
