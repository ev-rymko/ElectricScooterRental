package com.senla.finalProject.controllers;

import com.senla.finalProject.dto.LoginDto;
import com.senla.finalProject.dto.RentDto;
import com.senla.finalProject.iService.IRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/rentals")
@RequiredArgsConstructor
public class UserRentController {

    private final IRentService rentService;

    @PostMapping
    public ResponseEntity<String> addRent(@RequestBody RentDto dto) {
        rentService.save(dto);
        return ResponseEntity.ok("The rent has been successfully added.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentDto> updateRent(@PathVariable Long id,
                                              @RequestBody RentDto dto) {
        return ResponseEntity.ok(rentService.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<RentDto>> getRentalHistory(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(rentService.getRentalHistoryForClient(dto));
    }
}
