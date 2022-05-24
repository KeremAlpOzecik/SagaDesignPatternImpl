package com.keremalp.felxibleaccountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "flex_acc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlexAcc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerNumber;
    private Long accountNumber;
    private String customerName;
    private String address;
    private String phoneNumber;
    private Long  accountLimit;
    @Enumerated(EnumType.STRING)
    private FlexAccStatus flexAccStatus;

    public enum FlexAccStatus {
        OPEN,
        NOT_OPEN,
        CLOSED
    }
}
