package com.keremalp.managementservice.dto.products.credit_card;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccountForCreditCardDto {
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
