package com.keremalp.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Long customerNumber;
    private Long accountNumber;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;


    public enum AccountStatus {
        CREATED, NOT_CREATED
    }
}
