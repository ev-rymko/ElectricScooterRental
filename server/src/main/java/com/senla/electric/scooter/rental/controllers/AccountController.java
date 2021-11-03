package com.senla.electric.scooter.rental.controllers;

import com.senla.electric.scooter.rental.dto.AccountDto;
import com.senla.electric.scooter.rental.iService.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService;

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccountData(@PathVariable Long id,
                                                        @RequestParam AccountDto dto) {
        return ResponseEntity.ok(accountService.updateAccount(id, dto));
    }
}
