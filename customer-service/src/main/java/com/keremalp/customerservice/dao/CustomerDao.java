package com.keremalp.customerservice.dao;

import com.keremalp.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
    Customer findByCustomerNumber(Long customerNumber);

}
