package com.senla.electric.scooter.rental.controllers;

import com.senla.electric.scooter.rental.dto.AccountDto;
import com.senla.electric.scooter.rental.dto.UserDataDto;
import com.senla.electric.scooter.rental.dto.UserDto;
import com.senla.electric.scooter.rental.iService.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<UserDataDto> addUser(@RequestBody UserDataDto dto) {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        return ResponseEntity.ok(userService.save(dto, roles));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserData(@PathVariable Long id,
                                                  @RequestParam UserDto dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @PutMapping("/account/{id}")
    public ResponseEntity<AccountDto> updateAccountData(@PathVariable Long id,
                                                        @RequestParam AccountDto dto) {
        return ResponseEntity.ok(userService.updateAccount(id, dto));
    }

    @DeleteMapping
    public ResponseEntity<UserDto> deleteUser(@RequestParam UserDataDto dto) {
        return ResponseEntity.ok(userService.delete(dto));
    }

}
