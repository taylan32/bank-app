package com.example.customerservice.dto;

import com.example.customerservice.model.IndividualCustomer;
import org.springframework.stereotype.Component;

@Component
public class IndividualCustomerDtoConverter {

    public IndividualCustomerDto convert(IndividualCustomer from) {
        return new IndividualCustomerDto(
                from.getId(),
                from.getEmail(),
                from.getFirstName(),
                from.getMiddleName(),
                from.getLastName(),
                from.getBirthOfDate(),
                null
        );
    }

}
