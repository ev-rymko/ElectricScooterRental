package com.senla.finalProject.controllers;

import com.senla.finalProject.dto.AccountDto;
import com.senla.finalProject.dto.UserDataDto;
import com.senla.finalProject.dto.UserDto;
import com.senla.finalProject.iService.IUserService;
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
    public ResponseEntity<String> addUser(@RequestBody UserDataDto dto) {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        userService.save(dto, roles);
        return ResponseEntity.ok("The user has been successfully added.");
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
    public ResponseEntity<UserDataDto> deleteUser(@RequestParam UserDataDto dto) {
        return ResponseEntity.ok(userService.delete(dto));
    }

}
