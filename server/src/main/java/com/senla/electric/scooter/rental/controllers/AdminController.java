package com.senla.electric.scooter.rental.controllers;

import com.senla.electric.scooter.rental.dto.UserDataDto;
import com.senla.electric.scooter.rental.dto.UserDto;
import com.senla.electric.scooter.rental.iService.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final IUserService userService;

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDataDto dto) {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");
        return ResponseEntity.ok(userService.save(dto, roles));
    }
}
