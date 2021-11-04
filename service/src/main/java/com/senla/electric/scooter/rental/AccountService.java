package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.AccountDto;
import com.senla.electric.scooter.rental.dto.LoginDto;
import com.senla.electric.scooter.rental.dto.UserDataDto;
import com.senla.electric.scooter.rental.exceptions.BadDataException;
import com.senla.electric.scooter.rental.exceptions.DataDuplicationException;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.iDao.IAccountDao;
import com.senla.electric.scooter.rental.iDao.IRoleDao;
import com.senla.electric.scooter.rental.iService.IAccountService;
import com.senla.electric.scooter.rental.model.Account;
import com.senla.electric.scooter.rental.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService implements IAccountService {

    private static final String ACCOUNT_LOGIN_DUPLICATION_EXCEPTION = "An account with this login already exist. ";
    private static final String ACCOUNT_NOT_FOUND_BY_ID_EXCEPTION = "The account with this id not found. ";
    private static final String ACCOUNT_NOT_FOUND_BY_LOGIN_EXCEPTION = "The account with this login not found. ";
    private final IAccountDao accountDao;
    private final IRoleDao roleDao;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;

    @Override
    public AccountDto save(UserDataDto userDto, List<String> roleNames, User user) {
        if (accountDao.getUserByLogin(userDto.getLogin()) != null) {
            throw new DataDuplicationException(ACCOUNT_LOGIN_DUPLICATION_EXCEPTION);
        }
        Account account = new Account();
        account.setLogin(userDto.getLogin());
        account.setPassword(encoder.encode(userDto.getPassword()));
        account.setRoles(roleNames.stream()
                .map(roleDao::findByName)
                .collect(Collectors.toList()));
        account.setUser(user);

        Account savedAccount = accountDao.save(account);
        return mapper.map(savedAccount, AccountDto.class);
    }

    @Override
    public AccountDto update(Long id, LoginDto dto) {
        Account account = accountDao.getById(id);
        if (account == null) {
            throw new BadDataException(ACCOUNT_NOT_FOUND_BY_ID_EXCEPTION);
        }
        account.setLogin(dto.getLogin());
        account.setPassword(encoder.encode(dto.getPassword()));
        Account updateAccount = accountDao.update(id, account);
        return mapper.map(updateAccount, AccountDto.class);
    }

    @Override
    public AccountDto delete(UserDataDto user) {
        Account accountForDelete = accountDao.getUserByLogin(user.getLogin());
        if (accountForDelete == null) {
            throw new DataNotFoundException(ACCOUNT_NOT_FOUND_BY_LOGIN_EXCEPTION);
        }
        Account account = accountDao.delete(accountForDelete);
        return mapper.map(account, AccountDto.class);
    }

    @Override
    public AccountDto getUserByLogin(String login) {
        return mapper.map(accountDao.getUserByLogin(login), AccountDto.class);
    }
}
