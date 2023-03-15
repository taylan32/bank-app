package com.example.transactionservice.dto;

import com.example.transactionservice.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private String id;
    private IndividualCustomerDto sender;
    private IndividualCustomerDto receiver;
    private Date transactionDate;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String transactionMessage;


}
