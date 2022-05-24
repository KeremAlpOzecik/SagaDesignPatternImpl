package com.keremalp.managementservice.dto.events.create;

import com.keremalp.managementservice.dto.products.credit.CreateAccountForCreditDto;
import com.keremalp.managementservice.dto.products.credit_card.CreateAccountForCreditCardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateCreditCardForNotExistAccountEvent {
    private String transactionId;
    private CreateAccountForCreditCardDto customer;
    private Date date;
}
