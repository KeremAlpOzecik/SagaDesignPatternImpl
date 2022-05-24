package com.keremalp.creditcardservice.dto.products;

import com.keremalp.creditcardservice.entity.CreditCard;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateCreditCardDto {
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
    private CreditCard.CreditCardStatus creditCardStatus;
    private LocalDate creditOpenDate;


}
