package com.keremalp.customerservice.dto.events.create;

import com.keremalp.customerservice.dto.product.CreateCifOpenForCreditCardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreditCardCreateRequestEvent {
    private String transactionId;
    private CreateCifOpenForCreditCardDto customer;
    private Date date;
}