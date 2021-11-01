package com.senla.finalProject.controllers;

import com.senla.finalProject.dto.RentalPointDto;
import com.senla.finalProject.dto.ScooterDto;
import com.senla.finalProject.iService.IRentalPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/rental-point")
@RequiredArgsConstructor
public class UserRentalPointController {

    private final IRentalPointService rentalPointService;

    @GetMapping
    public ResponseEntity<List<RentalPointDto>> getAll() {
        return ResponseEntity.ok(rentalPointService.getAll());
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<RentalPointDto>> getAllInCountry(@PathVariable String country) {
        return ResponseEntity.ok(rentalPointService.getAllInCountry(country));
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<RentalPointDto>> getAllInCity(@PathVariable String city) {
        return ResponseEntity.ok(rentalPointService.getAllInCity(city));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ScooterDto>> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok(rentalPointService.getDetails(id));
    }
}
