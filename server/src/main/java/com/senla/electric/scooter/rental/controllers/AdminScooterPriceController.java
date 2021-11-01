package com.senla.finalProject.controllers;

import com.senla.finalProject.dto.ScooterPriceDto;
import com.senla.finalProject.iService.IScooterPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins/scooter-price")
@RequiredArgsConstructor
public class AdminScooterPriceController {

    private final IScooterPriceService scooterPriceService;

    @PostMapping
    public ResponseEntity<String> addScooterPrice(@RequestBody ScooterPriceDto dto){
        scooterPriceService.save(dto);
        return ResponseEntity.ok("The scooter price has been successfully added.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScooterPriceDto> updateScooterPrice(@PathVariable Long id,
                                                              @RequestBody ScooterPriceDto dto){
        return ResponseEntity.ok(scooterPriceService.update(id, dto));
    }
}
