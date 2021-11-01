package com.senla.finalProject.controllers;

import com.senla.finalProject.dto.ScooterDto;
import com.senla.finalProject.iService.IScooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins/scooters")
@RequiredArgsConstructor
public class AdminScooterController {

    private final IScooterService scooterService;

    @PostMapping
    public ResponseEntity<String> addScooter(@RequestBody ScooterDto dto) {
        scooterService.save(dto);
        return ResponseEntity.ok("The scooter has been successfully added.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScooterDto> updateScooter(@PathVariable Long id,
                                                    @RequestBody ScooterDto dto) {
        return ResponseEntity.ok(scooterService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ScooterDto> deleteScooter(@PathVariable Long id) {
        return ResponseEntity.ok(scooterService.delete(id));
    }
}
