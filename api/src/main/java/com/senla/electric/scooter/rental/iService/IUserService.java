package com.senla.electric.scooter.rental.iService;

import com.senla.electric.scooter.rental.dto.AccountDto;
import com.senla.electric.scooter.rental.dto.UserDataDto;
import com.senla.electric.scooter.rental.dto.UserDto;

import java.util.List;

public interface IUserService {

    UserDataDto save(UserDataDto user, List<String> roleNames);

    UserDto update(Long id, UserDto user);

    AccountDto updateAccount(Long id, AccountDto dto);

    UserDto delete(UserDataDto user);
}
