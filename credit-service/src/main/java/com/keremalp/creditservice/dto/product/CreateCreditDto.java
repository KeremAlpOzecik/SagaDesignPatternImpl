package com.keremalp.creditservice.dto.product;

import com.keremalp.creditservice.entity.Credit;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateCreditDto {
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
    private Credit.CreditStatus creditStatus;
    private LocalDate creditOpenDate;


}
