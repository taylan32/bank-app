package com.example.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerDto {

    private String id;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;
    private List<AccountDto> accounts;

}
