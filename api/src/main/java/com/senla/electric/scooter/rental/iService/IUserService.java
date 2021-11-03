package com.senla.electric.scooter.rental.iService;

import com.senla.electric.scooter.rental.dto.UserDataDto;
import com.senla.electric.scooter.rental.dto.UserDto;

import java.util.List;

public interface IUserService {

    UserDto save(UserDataDto user, List<String> roleNames);

    UserDto update(Long id, UserDto user);

    UserDto delete(UserDataDto user);

    List<UserDto> getAll();
}
