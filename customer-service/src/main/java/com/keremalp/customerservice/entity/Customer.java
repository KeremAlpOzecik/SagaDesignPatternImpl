package com.keremalp.customerservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Long customerNumber;
    @Enumerated(EnumType.STRING)
    private CustomerStatus customerStatus;

    public enum CustomerStatus {
        CREATED, NOT_CREATED
    }
}
