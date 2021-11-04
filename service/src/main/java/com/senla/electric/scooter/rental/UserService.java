package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.dto.UserDto;
import com.senla.electric.scooter.rental.exceptions.BadDataException;
import com.senla.electric.scooter.rental.exceptions.DataDuplicationException;
import com.senla.electric.scooter.rental.exceptions.DataNotFoundException;
import com.senla.electric.scooter.rental.dto.UserDataDto;
import com.senla.electric.scooter.rental.iDao.IUserDao;
import com.senla.electric.scooter.rental.iService.IAccountService;
import com.senla.electric.scooter.rental.iService.IUserService;
import com.senla.electric.scooter.rental.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements IUserService {

    private final IUserDao userDao;
    private final IAccountService accountService;
    private final ModelMapper mapper;

    private static final String USER_EMAIL_DUPLICATION_EXCEPTION = "The user with this email already exists.";
    private static final String USER_NOT_FOUND_BY_ID_EXCEPTION = "The user with this id was not found. ";
    private static final String USER_NOT_FOUND_BY_EMAIL_EXCEPTION = "The user with this email not found. ";

    @Override
    public UserDto save(UserDataDto user, List<String> roleNames) {
        if (userDao.getUserByEmail(user.getEmail()) != null) {
            throw new DataDuplicationException(USER_EMAIL_DUPLICATION_EXCEPTION);
        }
        User registeredUser = new User();
        registeredUser.setFirstName(user.getFirstName());
        registeredUser.setSecondName(user.getSecondName());
        registeredUser.setEmail(user.getEmail());

        User savedUser = userDao.save(registeredUser);
        accountService.save(user, roleNames, savedUser);

        return mapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto update(Long id, UserDto user) {
        if (userDao.getById(id) == null) {
            throw new BadDataException(USER_NOT_FOUND_BY_ID_EXCEPTION);
        }
        User updateUser = userDao.update(id, mapper.map(user, User.class));
        return mapper.map(userDao.update(id, updateUser), UserDto.class);
    }

    @Override
    public UserDto delete(UserDataDto user) {
        User userForDelete = userDao.getUserByEmail(user.getEmail());
        if (userForDelete == null) {
            throw new DataNotFoundException(USER_NOT_FOUND_BY_EMAIL_EXCEPTION);
        }
        accountService.delete(user);
        User deletedUser = userDao.delete(userDao.getUserByEmail(user.getEmail()));
        return mapper.map(deletedUser, UserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }
}
