package com.example.transactionservice.client;

import com.example.transactionservice.dto.AccountDto;
import com.example.transactionservice.dto.UpdateBalanceRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service", path = "/v1/api/account")
public interface AccountServiceClient {


    @GetMapping("/{accountNumber}")
    ResponseEntity<AccountDto> getByAccountNumber(@PathVariable String accountNumber);
    @PutMapping("/update-balance")
    ResponseEntity<Void> updateBalance(@RequestBody UpdateBalanceRequestDto request);


}
