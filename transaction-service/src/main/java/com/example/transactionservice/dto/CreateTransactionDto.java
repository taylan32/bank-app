package com.example.transactionservice.dto;

import com.example.transactionservice.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionDto {

    @NotBlank(message = "Sender cannot be null.")
    private String sender;

    @NotBlank(message = "Receiver cannot be null.")
    private String receiver;

    @Min(value = 1, message = "Transaction amount cannot be less than or equal to 0.")
    @NotNull(message = "Transaction amount cannot be null.")
    private BigDecimal amount;

    @Size(min = 1, max = 255, message = "Length of transaction message should be between 1 and 255 characters.")
    private String transactionMessage;

    @NotNull(message = "Transaction type cannot be null.")
    private TransactionType transactionType;

}
