package com.senla.electric.scooter.rental.controllers;

import com.senla.electric.scooter.rental.dto.ScooterPriceDto;
import com.senla.electric.scooter.rental.iService.IScooterPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scooter-prices")
@RequiredArgsConstructor
public class ScooterPriceController {

    private final IScooterPriceService scooterPriceService;

    @PostMapping("/admins")
    public ResponseEntity<String> addScooterPrice(@RequestBody ScooterPriceDto dto) {
        scooterPriceService.save(dto);
        return ResponseEntity.ok("The scooter price has been successfully added.");
    }

    @PutMapping("/admins/{id}")
    public ResponseEntity<ScooterPriceDto> updateScooterPrice(@PathVariable Long id,
                                                              @RequestBody ScooterPriceDto dto) {
        return ResponseEntity.ok(scooterPriceService.update(id, dto));
    }

    @DeleteMapping("/admins/{id}")
    public ResponseEntity<ScooterPriceDto> deleteScooterPrice(@PathVariable Long id) {
        return ResponseEntity.ok(scooterPriceService.delete(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<ScooterPriceDto>> getAll() {
        return ResponseEntity.ok(scooterPriceService.getAll());
    }
}
