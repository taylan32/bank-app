package com.example.accountservice.service;

import com.example.accountservice.dto.AccountDto;
import com.example.accountservice.dto.AccountDtoConverter;
import com.example.accountservice.dto.CreateAccountRequestDto;
import com.example.accountservice.dto.UpdateBalanceRequestDto;
import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository repository;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository repository, AccountDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public AccountDto createAccount() {
        return converter.convert(repository.save(new Account
                (
                        null,
                        UUID.randomUUID().toString(),
                        BigDecimal.ZERO,
                        null,
                        null,
                        false)
        ));
    }

    public AccountDto getById(String id) {
        return converter.convert(findAccountById(id));
    }

    protected Account findAccountById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Requested account not found with id " + id));
    }

    public AccountDto findAccountByAccountNumber(String accountNumber) {
        Account data =  repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Requested account not found"));
        return converter.convert(data);
    }

    protected Account getAccountByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Requested account not found"));
    }
    @Transactional
    public void updateBalance(UpdateBalanceRequestDto request) {
        Account sender = getAccountByAccountNumber(request.getSender());
        Account receiver = getAccountByAccountNumber(request.getReceiver());
        if(sender.equals(receiver)) {
            receiver.setBalance(new BigDecimal(receiver.getBalance().intValue() + request.getAmount().intValue()));
            repository.save(receiver);
        } else {
            sender.setBalance(new BigDecimal(sender.getBalance().intValue() - request.getAmount().intValue()));
            receiver.setBalance(new BigDecimal(receiver.getBalance().intValue() + request.getAmount().intValue()));
            repository.save(sender);
            repository.save(receiver);
        }


    }

}
