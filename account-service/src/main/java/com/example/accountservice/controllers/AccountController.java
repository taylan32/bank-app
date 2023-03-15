package com.example.accountservice.controllers;

import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.dto.CreateAccountRequestDto;
import com.example.accountservice.dto.UpdateBalanceRequestDto;
import com.example.accountservice.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/")
    public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid CreateAccountRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(request));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getById(id));
    }
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountDto> getByAccountNumber(@PathVariable String accountNumber)
     {
        return ResponseEntity.ok(accountService.findAccountByAccountNumber(accountNumber));
    }

    @PutMapping("/update-balance")
    public ResponseEntity<Void> updateBalance(@RequestBody @Valid UpdateBalanceRequestDto request) {
        accountService.updateBalance(request);
        return ResponseEntity.noContent().build();
    }

}
