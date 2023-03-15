package com.example.customerservice.repository;

import com.example.customerservice.model.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, String> {

    boolean existsByEmail(String email);
    boolean existsBySsn(String ssn);
}
