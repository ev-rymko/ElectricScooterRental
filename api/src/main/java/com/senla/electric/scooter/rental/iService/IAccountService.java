package com.senla.electric.scooter.rental.iService;

import com.senla.electric.scooter.rental.dto.AccountDto;
import com.senla.electric.scooter.rental.dto.LoginDto;
import com.senla.electric.scooter.rental.dto.UserDataDto;
import com.senla.electric.scooter.rental.model.User;

import java.util.List;

public interface IAccountService {

    AccountDto save(UserDataDto userDto, List<String> roleNames, User user);

    AccountDto update(Long id, LoginDto dto);

    AccountDto delete(UserDataDto user);

    AccountDto getUserByLogin(String login);

}
