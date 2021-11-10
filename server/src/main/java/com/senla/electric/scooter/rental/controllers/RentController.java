package com.senla.electric.scooter.rental.controllers;

import com.senla.electric.scooter.rental.dto.LoginDto;
import com.senla.electric.scooter.rental.dto.RentDto;
import com.senla.electric.scooter.rental.dto.RentForHourDto;
import com.senla.electric.scooter.rental.dto.SubscriptionRentDto;
import com.senla.electric.scooter.rental.iService.IRentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentController {

    private final IRentService rentService;

    @PostMapping("/users")
    public ResponseEntity<RentDto> add(@RequestBody RentForHourDto dto) {
        return ResponseEntity.ok(rentService.addForHour(dto));
    }

    @PostMapping("/users/subscription")
    public ResponseEntity<RentDto> addSubscription(@RequestBody SubscriptionRentDto dto) {
        return ResponseEntity.ok(rentService.addSubscription(dto));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<RentDto> update(@PathVariable Long id,
                                              @RequestBody RentDto dto) {
        return ResponseEntity.ok(rentService.update(id, dto));
    }

    @PutMapping("/users/mileage/{id}")
    public ResponseEntity<RentDto> addMileage(@PathVariable Long id,
                                              @RequestParam  double mileage){
        return ResponseEntity.ok(rentService.setMileage(id, mileage));
    }

    @GetMapping("/users")
    public ResponseEntity<List<RentDto>> getHistory(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(rentService.getHistoryForClient(dto));
    }

    @GetMapping("/admins")
    public ResponseEntity<List<RentDto>> getAll() {
        return ResponseEntity.ok(rentService.getAll());
    }

    @GetMapping("/admins/history")
    public ResponseEntity<List<RentDto>> getHistory(@RequestParam Long scooterId) {
        return ResponseEntity.ok(rentService.getHistoryForAdministrator(scooterId));
    }

    @PutMapping("/admins/new-price/{id}")
    public ResponseEntity<RentDto> setPrice(@PathVariable Long id,
                                            @RequestParam double newPrice) {
        return ResponseEntity.ok(rentService.setPrice(id, newPrice));
    }

    @PutMapping("/admins/discount/{id}")
    public ResponseEntity<RentDto> setDiscount(@PathVariable Long id,
                                               @RequestParam int percent) {
        return ResponseEntity.ok(rentService.setDiscount(id, percent));
    }

    @PutMapping("/users/finish/{id}")
    public ResponseEntity<RentDto> finishTrip(@PathVariable Long id){
        return ResponseEntity.ok(rentService.finishTrip(id));
    }
}
