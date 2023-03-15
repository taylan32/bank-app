package com.example.accountservice.dto;

import com.example.accountservice.model.enums.TransactionAspect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBalanceRequestDto {

    @NotBlank(message = "Sender account number cannot be null")
    private String sender;
    @NotBlank(message = "Receiver account number cannot be null")
    private String receiver;
    @NotNull(message = "Balance cannot be null")
    private BigDecimal amount;

}