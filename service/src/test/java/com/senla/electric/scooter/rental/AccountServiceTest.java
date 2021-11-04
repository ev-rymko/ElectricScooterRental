package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.AccountDto;
import com.senla.electric.scooter.rental.dto.LoginDto;
import com.senla.electric.scooter.rental.dto.UserDataDto;
import com.senla.electric.scooter.rental.exceptions.BadDataException;
import com.senla.electric.scooter.rental.exceptions.DataDuplicationException;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.model.Account;
import com.senla.electric.scooter.rental.model.Role;
import com.senla.electric.scooter.rental.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;
    @Mock
    AccountDao accountDao;
    @Mock
    RoleDao roleDao;
    @Mock
    ModelMapper mapper;
    @Mock
    PasswordEncoder encoder;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveTest(){
        AccountDto dto = new AccountDto();
        dto.setId(1L);
        Account account = new Account();
        account.setId(1L);
        UserDataDto userDataDto = new UserDataDto();
        User user = new User();
        List<String> roles = new ArrayList<>();
        Role role = new Role();

        Mockito.when(accountDao.getUserByLogin(userDataDto.getLogin())).thenReturn(null);
        Mockito.when(accountDao.save(any())).thenReturn(account);
        Mockito.when(mapper.map(dto, Account.class)).thenReturn(account);
        Mockito.when(mapper.map(account, AccountDto.class)).thenReturn(dto);
        Mockito.when(encoder.encode(userDataDto.getPassword())).thenReturn("test");
        Mockito.when(roleDao.findByName(any())).thenReturn(role);

        AccountDto savedAccount = accountService.save(userDataDto, roles, user);
        assertNotNull(savedAccount);
        assertEquals(1L, savedAccount.getId());
    }

    @Test
    void saveWithExceptionTest(){
        AccountDto dto = new AccountDto();
        dto.setId(1L);
        Account account = new Account();
        account.setId(1L);
        UserDataDto userDataDto = new UserDataDto();
        User user = new User();
        List<String> roles = new ArrayList<>();

        Mockito.when(accountDao.getUserByLogin(userDataDto.getLogin())).thenReturn(account);

        assertThrows(DataDuplicationException.class, () -> accountService.save(userDataDto, roles, user));
    }

    @Test
    void updateTest(){
        AccountDto dto = new AccountDto();
        dto.setId(1L);
        Account account = new Account();
        account.setId(1L);
        LoginDto loginDto = new LoginDto();

        Mockito.when(accountDao.update(1L, account)).thenReturn(account);
        Mockito.when(mapper.map(dto, Account.class)).thenReturn(account);
        Mockito.when(mapper.map(account, AccountDto.class)).thenReturn(dto);
        Mockito.when(accountDao.getById(1L)).thenReturn(account);

        AccountDto updatedAccount = accountService.update(1L, loginDto);
        assertNotNull(updatedAccount);
        assertEquals(1L, updatedAccount.getId());
    }

    @Test
    void updateWithExceptionTest(){
        LoginDto dto = new LoginDto();

        Mockito.when(accountDao.getById(1L)).thenReturn(null);

        assertThrows(BadDataException.class, () -> accountService.update(1L, dto));
    }

    @Test
    void deleteTest(){
        AccountDto dto = new AccountDto();
        dto.setId(1L);
        Account account = new Account();
        account.setId(1L);
        UserDataDto userDataDto = new UserDataDto();

        Mockito.when(accountDao.getUserByLogin(userDataDto.getLogin())).thenReturn(account);
        Mockito.when(accountDao.delete(account)).thenReturn(account);
        Mockito.when(mapper.map(account, AccountDto.class)).thenReturn(dto);

        AccountDto deletedAccount = accountService.delete(userDataDto);
        assertNotNull(deletedAccount);
        assertEquals(1L, deletedAccount.getId());
    }

    @Test
    void deleteWithExceptionTest(){
        UserDataDto userDataDto = new UserDataDto();

        Mockito.when(accountDao.getUserByLogin(userDataDto.getLogin())).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> accountService.delete(userDataDto));
    }
}
