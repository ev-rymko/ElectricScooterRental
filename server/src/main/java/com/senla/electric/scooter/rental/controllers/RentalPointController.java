package com.senla.electric.scooter.rental.controllers;

import com.senla.electric.scooter.rental.dto.RentalPointDto;
import com.senla.electric.scooter.rental.dto.ScooterDto;
import com.senla.electric.scooter.rental.iService.IRentalPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental-points")
@RequiredArgsConstructor
public class RentalPointController {

    private final IRentalPointService rentalPointService;

    @PostMapping("/admins")
    public ResponseEntity<RentalPointDto> addRentalPoint(@RequestBody RentalPointDto dto) {
        return ResponseEntity.ok(rentalPointService.save(dto));
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<RentalPointDto> updateRentalPoint(@PathVariable Long id,
                                                            @RequestBody RentalPointDto dto) {
        return ResponseEntity.ok(rentalPointService.update(id, dto));
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<RentalPointDto> deleteRentalPoint(@PathVariable Long id) {
        return ResponseEntity.ok(rentalPointService.delete(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<RentalPointDto>> getAll() {
        return ResponseEntity.ok(rentalPointService.getAll());
    }

    @GetMapping("/users/country/{country}")
    public ResponseEntity<List<RentalPointDto>> getAllInCountry(@PathVariable String country) {
        return ResponseEntity.ok(rentalPointService.getAllInCountry(country));
    }

    @GetMapping("/users/city/{city}")
    public ResponseEntity<List<RentalPointDto>> getAllInCity(@PathVariable String city) {
        return ResponseEntity.ok(rentalPointService.getAllInCity(city));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<ScooterDto>> getDetails(@PathVariable Long id) {
        return ResponseEntity.ok(rentalPointService.getDetails(id));
    }
}
