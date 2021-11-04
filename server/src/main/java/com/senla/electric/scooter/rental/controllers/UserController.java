package com.senla.electric.scooter.rental.controllers;

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
    public ResponseEntity<UserDto> addUser(@RequestBody UserDataDto dto) {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        return ResponseEntity.ok(userService.save(dto, roles));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                                  @RequestBody UserDto dto) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    @DeleteMapping
    public ResponseEntity<UserDto> deleteUser(@RequestBody UserDataDto dto) {
        return ResponseEntity.ok(userService.delete(dto));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

}
