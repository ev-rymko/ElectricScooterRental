package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.AccountDto;
import com.senla.electric.scooter.rental.dto.UserDto;
import com.senla.electric.scooter.rental.exceptions.BadDataException;
import com.senla.electric.scooter.rental.exceptions.DataDuplicationException;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.dto.UserDataDto;
import com.senla.electric.scooter.rental.iDao.IAccountDao;
import com.senla.electric.scooter.rental.iDao.IRoleDao;
import com.senla.electric.scooter.rental.iDao.IUserDao;
import com.senla.electric.scooter.rental.iService.IUserService;
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
public class UserService implements IUserService {

    private final IAccountDao accountDao;
    private final IUserDao userDao;
    private final IRoleDao roleDao;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;

    private static final String USER_EMAIL_DUPLICATION_EXCEPTION = "The user with this email already exists.";
    private static final String ACCOUNT_LOGIN_DUPLICATION_EXCEPTION = "An account with this login already exist. ";
    private static final String USER_NOT_FOUND_BY_ID_EXCEPTION = "The user with this id was not found. ";
    private static final String ACCOUNT_NOT_FOUND_BY_ID_EXCEPTION = "The account with this id not found. ";
    private static final String USER_NOT_FOUND_BY_EMAIL_EXCEPTION = "The user with this email not found. ";
    private static final String ACCOUNT_NOT_FOUND_BY_LOGIN_EXCEPTION = "The account with this login not found. ";

    @Override
    public UserDataDto save(UserDataDto user, List<String> roleNames) {
        if (userDao.getAll().size() > 0 && accountDao.getAll().size() > 0) {
            if (userDao.getUserByEmail(user.getEmail()) != null) {
                throw new DataDuplicationException(USER_EMAIL_DUPLICATION_EXCEPTION);
            }

            if (accountDao.getUserByLogin(user.getLogin()) != null) {
                throw new DataDuplicationException(ACCOUNT_LOGIN_DUPLICATION_EXCEPTION);
            }
        }

        User registeredUser = new User();
        registeredUser.setFirstName(user.getFirstName());
        registeredUser.setSecondName(user.getSecondName());
        registeredUser.setEmail(user.getEmail());

        Account account = new Account();
        account.setLogin(user.getLogin());
        account.setPassword(encoder.encode(user.getPassword()));
        account.setRoles(roleNames.stream()
                .map(roleDao::findByName)
                .collect(Collectors.toList()));
        account.setUser(registeredUser);

        userDao.save(registeredUser);
        accountDao.save(account);

        return user;
    }

    @Override
    public UserDto update(Long id, UserDto user) {
        User updateUser = mapper.map(user, User.class);
        if (userDao.getById(id) == null) {
            throw new BadDataException(USER_NOT_FOUND_BY_ID_EXCEPTION);
        }
        return mapper.map(userDao.update(id, updateUser), UserDto.class);
    }

    @Override
    public AccountDto updateAccount(Long id, AccountDto dto) {
        if (accountDao.getById(id) == null) {
            throw new BadDataException(ACCOUNT_NOT_FOUND_BY_ID_EXCEPTION);
        }
        Account updateAccount = accountDao.update(id, mapper.map(dto, Account.class));
        return mapper.map(updateAccount, AccountDto.class);
    }

    @Override
    public UserDto delete(UserDataDto user) {
        User userForDelete = userDao.getUserByEmail(user.getEmail());
        Account accountForDelete = accountDao.getUserByLogin(user.getLogin());
        if (userForDelete == null) {
            throw new DataNotFoundException(USER_NOT_FOUND_BY_EMAIL_EXCEPTION);
        }
        if (accountForDelete == null) {
            throw new DataNotFoundException(ACCOUNT_NOT_FOUND_BY_LOGIN_EXCEPTION);
        }
        User deletedUser = userDao.delete(userDao.getUserByEmail(user.getEmail()));
        accountDao.delete(accountDao.getUserByLogin(user.getLogin()));
        return mapper.map(deletedUser, UserDto.class);
    }
}
