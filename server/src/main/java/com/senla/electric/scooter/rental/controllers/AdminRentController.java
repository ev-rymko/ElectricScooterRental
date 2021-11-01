package com.senla.finalProject.controllers;

import com.senla.finalProject.dto.RentDto;
import com.senla.finalProject.iService.IRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins/rentals")
@RequiredArgsConstructor
public class AdminRentController {

    private final IRentService rentService;

    @GetMapping
    public ResponseEntity<List<RentDto>> getAll() {
        return ResponseEntity.ok(rentService.getAll());
    }

    @GetMapping("/history")
    public ResponseEntity<List<RentDto>> getRentalHistory(@RequestParam Long scooterId) {
        return ResponseEntity.ok(rentService.getRentalHistoryForAdministrator(scooterId));
    }

    @PutMapping("/new-price/{id}")
    public ResponseEntity<RentDto> setPrice(@PathVariable Long id,
                                            @RequestParam double newPrice) {
        return ResponseEntity.ok(rentService.setPrice(id, newPrice));
    }

    @PutMapping("/discount/{id}")
    public ResponseEntity<RentDto> setDiscount(@PathVariable Long id,
                                               @RequestParam int percent) {
        return ResponseEntity.ok(rentService.setDiscount(id, percent));
    }
}
