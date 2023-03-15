package com.example.accountservice.dto;

import com.example.accountservice.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {

    public AccountDto convert(Account from) {
        return new AccountDto(
                from.getId(),
                from.getAccountNumber(),
                from.getBalance()
        );
    }

}
