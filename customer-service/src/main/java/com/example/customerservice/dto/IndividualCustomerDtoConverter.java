package com.example.customerservice.dto;

import com.example.customerservice.client.AccountServiceClient;
import com.example.customerservice.model.IndividualCustomer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class IndividualCustomerDtoConverter {

    private final AccountServiceClient accountServiceClient;

    public IndividualCustomerDtoConverter(AccountServiceClient accountServiceClient) {
        this.accountServiceClient = accountServiceClient;
    }

    public IndividualCustomerDto convert(IndividualCustomer from) {
        return new IndividualCustomerDto(
                from.getId(),
                from.getEmail(),
                from.getFirstName(),
                from.getMiddleName(),
                from.getLastName(),
                from.getBirthOfDate(),
                from.getAccounts()
                        .stream()
                        .map(a -> accountServiceClient.getByAccountNumber(a).getBody())
                        .collect(Collectors.toList())
        );
    }

}
