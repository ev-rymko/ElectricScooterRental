package com.senla.finalProject.iService;

import com.senla.finalProject.dto.AccountDto;
import com.senla.finalProject.dto.UserDataDto;
import com.senla.finalProject.dto.UserDto;
import com.senla.finalProject.model.Role;

import java.util.List;

public interface IUserService {

    UserDataDto save(UserDataDto user, List<String> roleNames);

    UserDto update(Long id, UserDto user);

    AccountDto updateAccount(Long id, AccountDto dto);

    UserDataDto delete(UserDataDto user);
}
