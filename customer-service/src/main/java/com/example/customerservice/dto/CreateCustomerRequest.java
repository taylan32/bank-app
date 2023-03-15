package com.example.customerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@MappedSuperclass
public abstract class CreateCustomerRequest {

    @Email
    @NotBlank(message = "Email is not allowed to be empty")
    private String email;


}
