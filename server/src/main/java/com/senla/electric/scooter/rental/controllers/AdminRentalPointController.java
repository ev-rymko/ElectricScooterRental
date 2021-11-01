package com.senla.finalProject.controllers;

import com.senla.finalProject.dto.RentalPointDto;
import com.senla.finalProject.iService.IRentalPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins/rental-point")
@RequiredArgsConstructor
public class AdminRentalPointController {

    private final IRentalPointService rentalPointService;

    @PostMapping
    public ResponseEntity<String> addRentalPoint(@RequestBody RentalPointDto dto) {
        rentalPointService.save(dto);
        return ResponseEntity.ok("The rental point has been successfully added.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalPointDto> updateRentalPoint(@PathVariable Long id,
                                                            @RequestBody RentalPointDto dto) {
        return ResponseEntity.ok(rentalPointService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RentalPointDto> deleteRentalPoint(@PathVariable Long id) {
        return ResponseEntity.ok(rentalPointService.delete(id));
    }
}
