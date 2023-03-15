package com.example.transactionservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDto {
    private String id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String accountNumber;

}
