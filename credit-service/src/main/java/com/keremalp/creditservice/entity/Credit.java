package com.keremalp.creditservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "credit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerNumber;
    private Long accountNumber;
    private String creditNumber;
    private Long creditAmount;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;
    @CreatedDate
    private LocalDate createdDate;

    public enum CreditStatus {
        CREATE,
        NOT_CREATE
    }
}
