package com.senla.electric.scooter.rental.controllers;

import com.senla.electric.scooter.rental.dto.ScooterDto;
import com.senla.electric.scooter.rental.iService.IScooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scooters")
@RequiredArgsConstructor
public class ScooterController {

    private final IScooterService scooterService;

    @PostMapping("/admins")
    public ResponseEntity<String> addScooter(@RequestBody ScooterDto dto) {
        scooterService.save(dto);
        return ResponseEntity.ok("The scooter has been successfully added.");
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<ScooterDto> updateScooter(@PathVariable Long id,
                                                    @RequestBody ScooterDto dto) {
        return ResponseEntity.ok(scooterService.update(id, dto));
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<ScooterDto> deleteScooter(@PathVariable Long id) {
        return ResponseEntity.ok(scooterService.delete(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<ScooterDto>> getAll() {
        return ResponseEntity.ok(scooterService.getAll());
    }
}
