package com.example.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest extends CreateCustomerRequest {
    @NotBlank(message = "Customer name is not allowed to be empty")
    @Size(min = 2, max = 20, message = "Length of first name should be between 2 and 20 characters.")
    private String firstName;

    @Size(min = 2, max = 20, message = "Length of middle name should be between 2 and 20 characters.")
    private String middleName;

    @NotBlank(message = "Customer last name is not allowed to be empty")
    @Size(min = 2, max = 20, message = "Length of last name should be between 2 and 20 characters.")
    private String lastName;

    @NotNull(message = "Birth of date cannot be null.")
    private Date birthOfDate;

    @NotBlank(message = "SSN is not allowed to be empty.")
    @Size(min = 11, max = 11, message = "Length of SSN should be 11 characters.")
    private String ssn;

}
