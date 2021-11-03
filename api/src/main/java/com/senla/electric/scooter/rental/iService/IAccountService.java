package com.senla.electric.scooter.rental.iService;

import com.senla.electric.scooter.rental.dto.AccountDto;
import com.senla.electric.scooter.rental.dto.UserDataDto;
import com.senla.electric.scooter.rental.model.Account;
import com.senla.electric.scooter.rental.model.User;

import java.util.List;

public interface IAccountService {

    AccountDto save(UserDataDto userDto, List<String> roleNames, User user);

    AccountDto updateAccount(Long id, AccountDto dto);

    AccountDto delete(UserDataDto user);

    Account getUserByLogin(String login);

}
