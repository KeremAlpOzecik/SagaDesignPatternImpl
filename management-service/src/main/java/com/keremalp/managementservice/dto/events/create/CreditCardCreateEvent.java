package com.keremalp.managementservice.dto.events.create;

import com.keremalp.managementservice.dto.products.credit.CreateCreditDto;
import com.keremalp.managementservice.dto.products.credit_card.CreateCreditCardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreditCardCreateEvent {

    private String transactionId;
    private CreateCreditCardDto createCreditDto;
    private Date date;
}