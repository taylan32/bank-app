package com.example.customerservice.service;

import com.example.customerservice.client.AccountServiceClient;
import com.example.customerservice.dto.*;
import com.example.customerservice.exception.CustomerNotFoundException;
import com.example.customerservice.exception.EmailAlreadyExistException;
import com.example.customerservice.exception.SSNException;
import com.example.customerservice.model.IndividualCustomer;
import com.example.customerservice.model.enums.CustomerStatus;
import com.example.customerservice.repository.IndividualCustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualCustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;
    private final IndividualCustomerDtoConverter converter;
    private final AccountServiceClient accountServiceClient;


    public IndividualCustomerService(IndividualCustomerRepository individualCustomerRepository,
                                     IndividualCustomerDtoConverter converter,
                                     AccountServiceClient accountServiceClient) {
        this.individualCustomerRepository = individualCustomerRepository;
        this.converter = converter;
        this.accountServiceClient = accountServiceClient;
    }

    public void createIndividualCustomer(CreateIndividualCustomerRequest request) {
        checkIfEmailExists(request.getEmail());
        checkIfSSNExists(request.getSsn());

        IndividualCustomer customer = new IndividualCustomer(
                null,
                request.getFirstName(),
                request.getMiddleName(),
                request.getLastName(),
                request.getBirthOfDate(),
                request.getSsn()
        );
        customer.setEmail(request.getEmail());
        customer.setCustomerStatus(CustomerStatus.ACTIVE);
        customer.setDeleted(false);
        customer.setAccounts(new ArrayList<>());

        IndividualCustomer saved = individualCustomerRepository.save(customer);
    }

    private void checkIfEmailExists(String email) {
        if(individualCustomerRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistException("Email already in use");
        }
    }

    private void checkIfSSNExists(String ssn) {
        if(individualCustomerRepository.existsBySsn(ssn)) {
            throw new SSNException("SSN already exist");
        }
    }

    public List<IndividualCustomerDto> getAllIndividualCustomers() {
        return individualCustomerRepository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public IndividualCustomerDto getById(String id) {
        IndividualCustomer customer = findIndividualCustomerById(id);
        IndividualCustomerDto individualCustomerDto = new IndividualCustomerDto(
                customer.getId(),
                customer.getEmail(),
                customer.getFirstName(),
                customer.getMiddleName(),
                customer.getLastName(),
                customer.getBirthOfDate(),
                customer.getAccounts()
                        .stream()
                        .map(accountServiceClient::getByAccountNumber)
                        .map(ResponseEntity::getBody)
                        .collect(Collectors.toList())
        );
        return individualCustomerDto;
        //return converter.convert(findIndividualCustomerById(id));
    }
    protected IndividualCustomer findIndividualCustomerById(String id) {
        return individualCustomerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Individual customer not found with id " + id));
    }

    public void addAccountToCustomer(AddAccountRequest request) {
        String accountNumber = accountServiceClient.createAccount().getBody().getAccountNumber();
        IndividualCustomer customer = findIndividualCustomerById(request.getCustomerId());
        customer.getAccounts().add(accountNumber);
        individualCustomerRepository.save(customer);

    }


}
