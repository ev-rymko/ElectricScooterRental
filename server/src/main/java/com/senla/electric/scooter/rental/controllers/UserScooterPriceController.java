package com.senla.finalProject.controllers;

import com.senla.finalProject.dto.ScooterPriceDto;
import com.senla.finalProject.iService.IScooterPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/scooter-price")
@RequiredArgsConstructor
public class UserScooterPriceController {

    private final IScooterPriceService scooterPriceService;

    @GetMapping
    public ResponseEntity<List<ScooterPriceDto>> getAll(){
        return ResponseEntity.ok(scooterPriceService.getAll());
    }
}
