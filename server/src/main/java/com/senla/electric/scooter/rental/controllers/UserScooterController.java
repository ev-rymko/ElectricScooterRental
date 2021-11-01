package com.senla.finalProject.controllers;

import com.senla.finalProject.dto.ScooterDto;
import com.senla.finalProject.iService.IScooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/scooters")
@RequiredArgsConstructor
public class UserScooterController {

    private final IScooterService scooterService;

    @GetMapping
    public ResponseEntity<List<ScooterDto>> getAll() {
        return ResponseEntity.ok(scooterService.getAll());
    }
}
