package com.keremalp.managementservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerNumber;
    private String contract;
    private String transactionId;
    private String transactionType;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    public enum TransactionStatus {
        NEW,
        DONE,
        CANCELED
    }
}
