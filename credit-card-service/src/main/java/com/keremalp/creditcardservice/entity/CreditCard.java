package com.keremalp.creditcardservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "credit_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerNumber;
    private Long accountNumber;
    private String creditCardNumber;
    private Long creditCardLimit;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private CreditCardStatus creditCardStatus;
    @CreatedDate
    private LocalDate createdDate;

    public enum CreditCardStatus {
        CREATE,
        NOT_CREATE
    }
}
