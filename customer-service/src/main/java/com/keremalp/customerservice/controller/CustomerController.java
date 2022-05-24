package com.keremalp.customerservice.controller;

import com.keremalp.customerservice.entity.Customer;
import com.keremalp.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")

public class CustomerController {
private final CustomerService customerService;
@GetMapping("/getCustomer")
public ResponseEntity<Customer> getCustomer(@RequestParam("customerNumber") Long customerNumber){
    return ResponseEntity.ok(customerService.getCustomer(customerNumber));
}

}
