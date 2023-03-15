package com.example.transactionservice.client;

import com.example.transactionservice.dto.IndividualCustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", path = "/v1/api/individual-customer")
public interface CustomerServiceClient {
    @GetMapping("/{id}")
    ResponseEntity<IndividualCustomerDto> getById(@PathVariable String id);
}
