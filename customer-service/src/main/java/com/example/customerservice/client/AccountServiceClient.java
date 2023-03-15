package com.example.customerservice.client;

import com.example.customerservice.dto.AccountDto;
import com.example.customerservice.dto.CreateAccountRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "account-service", path = "/v1/api/account")
public interface AccountServiceClient {

    @PostMapping("/")
    ResponseEntity<AccountDto> createAccount(@RequestBody @Valid CreateAccountRequestDto request);

    @GetMapping("/getById/{id}")
    ResponseEntity<AccountDto> getById(@PathVariable String id);

    @GetMapping("/{accountNumber}")
    ResponseEntity<AccountDto> getByAccountNumber(@PathVariable String accountNumber);

}
