package com.example.transactionservice.service;

import com.example.transactionservice.client.AccountServiceClient;
import com.example.transactionservice.client.CustomerServiceClient;
import com.example.transactionservice.dto.*;
import com.example.transactionservice.exception.InSufficientBalanceException;
import com.example.transactionservice.model.Transaction;
import com.example.transactionservice.model.TransactionType;
import com.example.transactionservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountServiceClient accountServiceClient;
    private final CustomerServiceClient customerServiceClient;

    public TransactionService(TransactionRepository transactionRepository,
                              AccountServiceClient accountServiceClient,
                              CustomerServiceClient customerServiceClient) {
        this.transactionRepository = transactionRepository;
        this.accountServiceClient = accountServiceClient;
        this.customerServiceClient = customerServiceClient;
    }

    @Transactional
    public void createTransaction(CreateTransactionDto request) {
        AccountDto sender = accountServiceClient.getByAccountNumber(request.getSender()).getBody();
        AccountDto receiver = accountServiceClient.getByAccountNumber(request.getReceiver()).getBody();

        checkIfBalanceIsSufficient(sender.getBalance(), request.getAmount());
        Transaction transaction = transactionRepository.save(new Transaction(
                null,
                sender.getAccountNumber(),
                receiver.getAccountNumber(),
                request.getAmount(),
                null,
                request.getTransactionMessage(),
                request.getTransactionType()
        ));
        accountServiceClient.updateBalance(new UpdateBalanceRequestDto(
                sender.getAccountNumber(),
                receiver.getAccountNumber(),
                request.getAmount())
        );

    }

    public TransactionDto getById(String id) {
        Transaction transaction = findTransactionById(id);
        TransactionDto transactionDto = new TransactionDto(
                transaction.getId(),
                customerServiceClient.getById(transaction.getSender()).getBody(),
                customerServiceClient.getById(transaction.getReceiver()).getBody(),
                transaction.getTransactionDate(),
                transaction.getTransactionType(),
                transaction.getAmount(),
                transaction.getTransactionMessage()

        );
        return transactionDto;
    }

    protected Transaction findTransactionById(String id) {
        return transactionRepository.findById(id)
                .orElseThrow(RuntimeException::new);

    }

    private void checkIfBalanceIsSufficient(BigDecimal balance, BigDecimal transactionAmount) {
        if(!(balance.compareTo(transactionAmount) >= 0)) {
            throw new InSufficientBalanceException("Sender account do not have enough amount of money.");
        }

    }

}
