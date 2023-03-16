package com.example.customerservice.controller;

import com.example.customerservice.dto.AddAccountRequest;
import com.example.customerservice.dto.CreateIndividualCustomerRequest;
import com.example.customerservice.dto.IndividualCustomerDto;
import com.example.customerservice.service.IndividualCustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/individual-customer")
public class IndividualCustomerController {

    private final Logger logger = LoggerFactory.getLogger(IndividualCustomerService.class);
    private final IndividualCustomerService customerService;


    public IndividualCustomerController(IndividualCustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> createIndividualCustomer(@RequestBody @Valid CreateIndividualCustomerRequest request) {
        customerService.createIndividualCustomer(request);
        logger.info("Customer created.");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<IndividualCustomerDto>> getAllIndividualCustomers() {
        return ResponseEntity.ok(customerService.getAllIndividualCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IndividualCustomerDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PutMapping("/add-account")
    public ResponseEntity<Void> addAccountToCustomer(@RequestBody @Valid AddAccountRequest request) {
        customerService.addAccountToCustomer(request);
        logger.info("Account added to customer with id: " + request.getCustomerId());
        return ResponseEntity.noContent().build();
    }

}
