package com.keremalp.accountservice.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCifOPENForCreditCardDto {
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

}