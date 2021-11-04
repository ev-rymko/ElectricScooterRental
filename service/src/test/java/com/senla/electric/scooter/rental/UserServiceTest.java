package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.AccountDto;
import com.senla.electric.scooter.rental.dto.UserDataDto;
import com.senla.electric.scooter.rental.dto.UserDto;
import com.senla.electric.scooter.rental.exceptions.BadDataException;
import com.senla.electric.scooter.rental.exceptions.DataDuplicationException;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserDao userDao;
    @Mock
    ModelMapper mapper;
    @Mock
    AccountService accountService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTest() {
        User user = new User();
        user.setId(1L);
        UserDto dto = new UserDto();
        dto.setId(1L);
        UserDataDto userDataDto = new UserDataDto();
        List<String> roleName = new ArrayList<>();

        Mockito.when(userDao.getUserByEmail(userDataDto.getEmail())).thenReturn(null);
        Mockito.when(userDao.save(any())).thenReturn(user);
        Mockito.when(mapper.map(user, UserDto.class)).thenReturn(dto);
        Mockito.when(accountService.save(userDataDto, roleName, user)).thenReturn(new AccountDto());

        UserDto savedUser = userService.save(userDataDto, roleName);
        assertNotNull(savedUser);
        assertEquals(1L, savedUser.getId());
    }

    @Test
    void saveWithException() {
        User user = new User();
        user.setId(1L);
        UserDataDto userDataDto = new UserDataDto();
        List<String> roleName = new ArrayList<>();

        Mockito.when(userDao.getUserByEmail(userDataDto.getEmail())).thenReturn(user);

        assertThrows(DataDuplicationException.class, () -> userService.save(userDataDto, roleName));
    }

    @Test
    void updateTest() {
        User user = new User();
        user.setId(1L);
        UserDto dto = new UserDto();
        dto.setId(1L);

        Mockito.when(userDao.getById(1L)).thenReturn(user);
        Mockito.when(userDao.update(1L, user)).thenReturn(user);
        Mockito.when(mapper.map(user, UserDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(dto, User.class)).thenReturn(user);

        UserDto updatedUser = userService.update(1L, dto);
        assertNotNull(updatedUser);
        assertEquals(1L, updatedUser.getId());
    }

    @Test
    void updateWithExceptionTest() {
        UserDto dto = new UserDto();
        dto.setId(1L);

        Mockito.when(userDao.getById(1L)).thenReturn(null);

        assertThrows(BadDataException.class, () -> userService.update(1L, dto));
    }

    @Test
    void deleteTest() {
        User user = new User();
        user.setId(1L);
        UserDto dto = new UserDto();
        dto.setId(1L);
        UserDataDto userDataDto = new UserDataDto();
        AccountDto account = new AccountDto();

        Mockito.when(userDao.getUserByEmail(userDataDto.getEmail())).thenReturn(user);
        Mockito.when(userDao.delete(user)).thenReturn(user);
        Mockito.when(accountService.delete(userDataDto)).thenReturn(account);
        Mockito.when(mapper.map(user, UserDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(dto, User.class)).thenReturn(user);

        UserDto deletedUser = userService.delete(userDataDto);
        assertNotNull(deletedUser);
        assertEquals(1L, deletedUser.getId());
    }

    @Test
    void deleteWithExceptionTest() {
        UserDataDto userDataDto = new UserDataDto();

        Mockito.when(userDao.getUserByEmail(userDataDto.getEmail())).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> userService.delete(userDataDto));
    }

    @Test
    void getAllTest() {
        User user = new User();
        User user1 = new User();
        UserDto dto = new UserDto();

        Mockito.when(mapper.map(user, UserDto.class)).thenReturn(dto);
        Mockito.when(mapper.map(user1, UserDto.class)).thenReturn(dto);
        Mockito.when(userDao.getAll()).thenReturn(Arrays.asList(user, user1));

        List<UserDto> users = userService.getAll();
        assertNotNull(users);
        assertEquals(2, users.size());
    }
}
