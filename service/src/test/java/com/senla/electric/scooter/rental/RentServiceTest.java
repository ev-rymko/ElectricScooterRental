package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.*;
import com.senla.electric.scooter.rental.enums.Subscription;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.exceptions.InvalidPriceException;
import com.senla.electric.scooter.rental.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RentServiceTest {

    @InjectMocks
    RentService rentService;
    @Mock
    RentDao rentDao;
    @Mock
    AccountService accountService;
    @Mock
    ScooterPriceService scooterPriceService;
    @Mock
    ModelMapper mapper;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addForHourTest() {
        RentForHourDto dto = new RentForHourDto(new AccountDto(), new RentalPointDto(), new ScooterDto(), 2);
        Account account = new Account();
        RentalPoint rentalPoint = new RentalPoint();
        ScooterPrice scooterPrice = new ScooterPrice();
        scooterPrice.setId(2L);
        scooterPrice.setPricePerHour(4);
        scooterPrice.setSubscriptionPricePerDay(5);
        scooterPrice.setScooterType("type");
        Scooter scooter = new Scooter();
        scooter.setScooterPrice(scooterPrice);
        Rent rent = new Rent.Builder()
                .withAccount(account)
                .withRentalPoint(rentalPoint)
                .withScooter(scooter)
                .withHours(2)
                .build();

        ScooterPriceDto scooterPriceDto = new ScooterPriceDto();
        scooterPriceDto.setId(2L);
        scooterPriceDto.setPricePerHour(4);
        scooterPriceDto.setSubscriptionPricePerDay(5);
        scooterPriceDto.setScooterType("type");
        ScooterDto scooterDto = new ScooterDto();
        scooterDto.setScooterPrice(scooterPriceDto);
        RentDto rentDto = new RentDto();
        rentDto.setId(1L);
        rentDto.setRentDate(LocalDateTime.now());
        rentDto.setAccount(new AccountDto());
        rentDto.setScooter(scooterDto);
        rentDto.setRentalPoint(new RentalPointDto());
        rentDto.setHours(2);

        Mockito.when(rentDao.save(rent)).thenReturn(rent);
        Mockito.when(scooterPriceService.findByName(rentDto.getScooter().getScooterPrice().getScooterType())).thenReturn(scooterPriceDto);

        Mockito.when(mapper.map(dto.getAccount(), Account.class)).thenReturn(account);
        Mockito.when(mapper.map(dto.getScooter(), Scooter.class)).thenReturn(scooter);
        Mockito.when(mapper.map(dto.getRentalPoint(), RentalPoint.class)).thenReturn(rentalPoint);
        Mockito.when(mapper.map(dto, Rent.class)).thenReturn(rent);
        Mockito.when(mapper.map(dto, RentDto.class)).thenReturn(rentDto);
        Mockito.when(mapper.map(rent, RentDto.class)).thenReturn(rentDto);
        Mockito.when(mapper.map(scooterPriceDto, ScooterPrice.class)).thenReturn(scooterPrice);

        RentDto resultRent = rentService.addForHour(dto);

        assertNotNull(resultRent);
        assertEquals(1L, resultRent.getId());
    }

    @Test
    void addSubscriptionTest() {
        SubscriptionRentDto dto = new SubscriptionRentDto(new AccountDto(), new RentalPointDto(), new ScooterDto(), Subscription.DAY);
        Account account = new Account();
        RentalPoint rentalPoint = new RentalPoint();
        ScooterPrice scooterPrice = new ScooterPrice();
        scooterPrice.setId(2L);
        scooterPrice.setPricePerHour(4);
        scooterPrice.setSubscriptionPricePerDay(5);
        scooterPrice.setScooterType("type");
        Scooter scooter = new Scooter();
        scooter.setScooterPrice(scooterPrice);
        Rent rent = new Rent.Builder()
                .withAccount(account)
                .withRentalPoint(rentalPoint)
                .withScooter(scooter)
                .withSubscription(Subscription.DAY)
                .build();

        ScooterPriceDto scooterPriceDto = new ScooterPriceDto();
        scooterPriceDto.setId(2L);
        scooterPriceDto.setPricePerHour(4);
        scooterPriceDto.setSubscriptionPricePerDay(5);
        scooterPriceDto.setScooterType("type");
        ScooterDto scooterDto = new ScooterDto();
        scooterDto.setScooterPrice(scooterPriceDto);
        RentDto rentDto = new RentDto();
        rentDto.setId(1L);
        rentDto.setRentDate(LocalDateTime.now());
        rentDto.setAccount(new AccountDto());
        rentDto.setScooter(scooterDto);
        rentDto.setRentalPoint(new RentalPointDto());
        rentDto.setSubscription(Subscription.DAY);

        Mockito.when(rentDao.save(rent)).thenReturn(rent);
        Mockito.when(scooterPriceService.findByName(rentDto.getScooter().getScooterPrice().getScooterType())).thenReturn(scooterPriceDto);

        Mockito.when(mapper.map(dto.getAccount(), Account.class)).thenReturn(account);
        Mockito.when(mapper.map(dto.getScooter(), Scooter.class)).thenReturn(scooter);
        Mockito.when(mapper.map(dto.getRentalPoint(), RentalPoint.class)).thenReturn(rentalPoint);
        Mockito.when(mapper.map(dto, Rent.class)).thenReturn(rent);
        Mockito.when(mapper.map(dto, RentDto.class)).thenReturn(rentDto);
        Mockito.when(mapper.map(rent, RentDto.class)).thenReturn(rentDto);
        Mockito.when(mapper.map(scooterPriceDto, ScooterPrice.class)).thenReturn(scooterPrice);

        RentDto resultRent = rentService.addSubscription(dto);

        assertNotNull(resultRent);
        assertEquals(1L, resultRent.getId());
    }

    @Test
    void setMileageTest() {
        Rent rent = new Rent.Builder()
                .withAccount(new Account())
                .withRentalPoint(new RentalPoint())
                .withScooter(new Scooter())
                .withSubscription(Subscription.DAY)
                .build();
        RentDto rentDto = new RentDto();
        rentDto.setId(1L);
        rentDto.setRentDate(LocalDateTime.now());
        rentDto.setAccount(new AccountDto());
        rentDto.setScooter(new ScooterDto());
        rentDto.setRentalPoint(new RentalPointDto());
        rentDto.setSubscription(Subscription.DAY);

        Mockito.when(rentDao.getById(1L)).thenReturn(rent);
        Mockito.when(rentDao.update(1L, rent)).thenReturn(rent);
        Mockito.when(mapper.map(rent, RentDto.class)).thenReturn(rentDto);

        RentDto resultRent = rentService.setMileage(1L, 20);

        assertNotNull(resultRent);
        assertEquals(1L, resultRent.getId());
    }

    @Test
    void setMileageWithExceptionTest() {
        Mockito.when(rentDao.getById(1L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> rentService.setMileage(1L, 20));
    }

    @Test
    void updateTest() {
        ScooterPrice scooterPrice = new ScooterPrice();
        scooterPrice.setId(2L);
        scooterPrice.setPricePerHour(4);
        scooterPrice.setSubscriptionPricePerDay(5);
        scooterPrice.setScooterType("type");
        Scooter scooter = new Scooter();
        scooter.setScooterPrice(scooterPrice);
        Rent rent = new Rent.Builder()
                .withAccount(new Account())
                .withRentalPoint(new RentalPoint())
                .withScooter(scooter)
                .withSubscription(Subscription.DAY)
                .build();

        ScooterPriceDto scooterPriceDto = new ScooterPriceDto();
        scooterPriceDto.setId(2L);
        scooterPriceDto.setPricePerHour(4);
        scooterPriceDto.setSubscriptionPricePerDay(5);
        scooterPriceDto.setScooterType("type");
        ScooterDto scooterDto = new ScooterDto();
        scooterDto.setScooterPrice(scooterPriceDto);
        AccountDto accountDto = new AccountDto();
        RentDto rentDto = new RentDto();
        rentDto.setId(1L);
        rentDto.setRentDate(LocalDateTime.now());
        rentDto.setAccount(accountDto);
        rentDto.setScooter(scooterDto);
        rentDto.setRentalPoint(new RentalPointDto());
        rentDto.setSubscription(Subscription.DAY);

        Mockito.when(rentDao.getById(1L)).thenReturn(rent);
        Mockito.when(rentDao.update(1L, rent)).thenReturn(rent);
        Mockito.when(mapper.map(rent, RentDto.class)).thenReturn(rentDto);
        Mockito.when(mapper.map(rentDto, Rent.class)).thenReturn(rent);

        Mockito.when(scooterPriceService.findByName(rentDto.getScooter().getScooterPrice().getScooterType())).thenReturn(scooterPriceDto);
        Mockito.when(mapper.map(scooterPriceDto, ScooterPrice.class)).thenReturn(scooterPrice);

        RentDto resultRent = rentService.update(1L, rentDto);

        assertNotNull(resultRent);
        assertEquals(1L, resultRent.getId());
    }

    @Test
    void updateWithExceptionTest() {
        Mockito.when(rentDao.getById(1L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> rentService.update(1L, new RentDto()));
    }

    @Test
    void getAllTest() {
        Rent rent = new Rent();
        Rent rent1 = new Rent();
        RentDto dto = new RentDto();

        Mockito.when(mapper.map(rent, RentDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(rent1, RentDto.class)).thenReturn(dto);
        Mockito.when(rentDao.getAll()).thenReturn(Arrays.asList(rent, rent1));

        List<RentDto> rentals = rentService.getAll();
        assertNotNull(rentals);
        assertEquals(2, rentals.size());
    }

    @Test
    void getHistoryForAdministratorTest() {
        Rent rent = new Rent();
        Rent rent1 = new Rent();
        RentDto dto = new RentDto();

        Mockito.when(mapper.map(rent, RentDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(rent1, RentDto.class)).thenReturn(dto);
        Mockito.when(rentDao.getRentalHistoryForAdministrator(1L)).thenReturn(Arrays.asList(rent, rent1));

        List<RentDto> rentals = rentService.getHistoryForAdministrator(1L);
        assertNotNull(rentals);
        assertEquals(2, rentals.size());
    }

    @Test
    void getHistoryForAdministratorWithExceptionTest() {
        Mockito.when(rentDao.getRentalHistoryForAdministrator(1L)).thenReturn(Collections.emptyList());

        assertThrows(DataNotFoundException.class, () -> rentService.getHistoryForAdministrator(1L));
    }

    @Test
    void getHistoryForClientTest() {
        LoginDto loginDto = new LoginDto("test", "test");
        Account account = new Account();
        AccountDto accountDto = new AccountDto();
        Rent rent = new Rent();
        Rent rent1 = new Rent();
        RentDto dto = new RentDto();

        Mockito.when(mapper.map(rent, RentDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(rent1, RentDto.class)).thenReturn(dto);
        Mockito.when(rentDao.getRentalHistoryForClient(account)).thenReturn(Arrays.asList(rent, rent1));
        Mockito.when(accountService.getUserByLogin(loginDto.getLogin())).thenReturn(accountDto);
        Mockito.when(mapper.map(accountDto, Account.class)).thenReturn(account);

        List<RentDto> rentals = rentService.getHistoryForClient(loginDto);
        assertNotNull(rentals);
        assertEquals(2, rentals.size());
    }

    @Test
    void getHistoryForClientWithExceptionTest() {
        LoginDto loginDto = new LoginDto("test", "test");
        Account account = new Account();
        AccountDto accountDto = new AccountDto();

        Mockito.when(accountService.getUserByLogin(loginDto.getLogin())).thenReturn(accountDto);
        Mockito.when(rentDao.getRentalHistoryForClient(account)).thenReturn(Collections.emptyList());
        Mockito.when(mapper.map(accountDto, Account.class)).thenReturn(account);

        assertThrows(DataNotFoundException.class, () -> rentService.getHistoryForClient(loginDto));
    }

    @Test
    void setPriceTest() {
        Rent rent = new Rent();
        RentDto dto = new RentDto();
        dto.setId(1L);

        Mockito.when(rentDao.getById(1L)).thenReturn(rent);
        Mockito.when(rentDao.update(1L, rent)).thenReturn(rent);
        Mockito.when(mapper.map(rent, RentDto.class)).thenReturn(dto);

        RentDto resultRent = rentService.setPrice(1L, 20);

        assertNotNull(resultRent);
        assertEquals(1L, resultRent.getId());
    }

    @Test
    void setPriceWithInvalidPriceException() {
        assertThrows(InvalidPriceException.class, () -> rentService.setPrice(1L, -1));
    }

    @Test
    void setPriceWithDataNotFoundException() {
        Mockito.when(rentDao.getById(1L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> rentService.setPrice(1L, 20));
    }

    @Test
    void setDiscountTest() {
        Rent rent = new Rent();
        RentDto dto = new RentDto();
        dto.setId(1L);

        Mockito.when(rentDao.getById(1L)).thenReturn(rent);
        Mockito.when(rentDao.update(1L, rent)).thenReturn(rent);
        Mockito.when(mapper.map(rent, RentDto.class)).thenReturn(dto);

        RentDto resultRent = rentService.setDiscount(1L, 20);

        assertNotNull(resultRent);
        assertEquals(1L, resultRent.getId());
    }

    @Test
    void setDiscountWithInvalidPriceException() {
        Rent rent = new Rent();
        rent.setFinalPrice(15);

        Mockito.when(rentDao.getById(1L)).thenReturn(rent);

        assertThrows(InvalidPriceException.class, () -> rentService.setDiscount(1L, -1));
    }

    @Test
    void setDiscountWithDataNotFoundException() {
        Mockito.when(rentDao.getById(1L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> rentService.setPrice(1L, 20));
    }
}
