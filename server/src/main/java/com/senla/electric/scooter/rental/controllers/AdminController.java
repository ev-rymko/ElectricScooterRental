package com.senla.finalProject.controllers;

import com.senla.finalProject.dto.UserDataDto;
import com.senla.finalProject.iService.IUserService;
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
    public ResponseEntity<String> addUser(@RequestBody UserDataDto dto) {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");
        userService.save(dto, roles);
        return ResponseEntity.ok("The admin has been successfully added.");
    }
}
