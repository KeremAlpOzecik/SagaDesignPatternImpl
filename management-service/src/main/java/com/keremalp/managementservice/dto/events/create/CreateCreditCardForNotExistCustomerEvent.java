package com.keremalp.managementservice.dto.events.create;

import com.keremalp.managementservice.dto.products.credit.CreateCifOpenForCreditDto;
import com.keremalp.managementservice.dto.products.credit_card.CreateCifOpenForCreditCardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateCreditCardForNotExistCustomerEvent {

    private String transactionId;
    private CreateCifOpenForCreditCardDto customer;
    private Date date;
}