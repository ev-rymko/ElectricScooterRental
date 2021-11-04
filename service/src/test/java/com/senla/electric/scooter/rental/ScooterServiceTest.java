package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.ScooterDto;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
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

public class ScooterServiceTest {

    @InjectMocks
    ScooterService scooterService;
    @Mock
    ScooterDao scooterDao;
    @Mock
    ModelMapper mapper;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTest(){
        Scooter scooter = new Scooter();
        scooter.setId(1L);
        ScooterDto dto = new ScooterDto();
        dto.setId(1L);

        Mockito.when(mapper.map(scooter, ScooterDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(dto, Scooter.class)).thenReturn(scooter);
        Mockito.when(scooterDao.save(scooter)).thenReturn(scooter);

        ScooterDto savedDto = scooterService.save(dto);

        assertNotNull(savedDto);
        assertEquals(1L, savedDto.getId());
    }

    @Test
    void updateTest(){
        Scooter scooter = new Scooter();
        scooter.setId(1L);
        ScooterDto dto = new ScooterDto();
        dto.setId(1L);

        Mockito.when(mapper.map(scooter, ScooterDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(dto, Scooter.class)).thenReturn(scooter);
        Mockito.when(scooterDao.update(1L, scooter)).thenReturn(scooter);
        Mockito.when(scooterDao.getById(1L)).thenReturn(scooter);

        ScooterDto updatedDto = scooterService.update(1L, dto);

        assertNotNull(updatedDto);
        assertEquals(1L, updatedDto.getId());
    }

    @Test
    void updateWithBadIdExceptionTest(){
        ScooterDto dto = new ScooterDto();
        dto.setId(1L);

        Mockito.when(scooterDao.getById(1L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> scooterService.update(1L, dto));
    }

    @Test
    void deleteTest(){
        Scooter scooter = new Scooter();
        scooter.setId(1L);
        ScooterDto dto = new ScooterDto();
        dto.setId(1L);

        Mockito.when(mapper.map(scooter, ScooterDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(dto, Scooter.class)).thenReturn(scooter);
        Mockito.when(scooterDao.delete(scooter)).thenReturn(scooter);
        Mockito.when(scooterDao.getById(1L)).thenReturn(scooter);

        ScooterDto deletedDto = scooterService.delete(1L);

        assertNotNull(deletedDto);
        assertEquals(1L, deletedDto.getId());
    }

    @Test
    void getAllTest(){
        Scooter scooter = new Scooter();
        scooter.setId(1L);
        Scooter scooter1 = new Scooter();
        scooter1.setId(2L);
        ScooterDto dto = new ScooterDto();
        dto.setId(1L);

        Mockito.when(mapper.map(scooter, ScooterDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(scooter1, ScooterDto.class)).thenReturn(dto);
        Mockito.when(scooterDao.getAll()).thenReturn(Arrays.asList(scooter, scooter1));

        List<ScooterDto> scooters = scooterService.getAll();

        assertNotNull(scooters);
        assertEquals(2, scooters.size());
    }

}
