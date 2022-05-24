package com.keremalp.managementservice.dto.events.cancel;


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
public class CreditCardTransactionCancelEvent {
    private String transactionId;
    private CreateCreditCardDto creditDto;
    private Date date;
}
