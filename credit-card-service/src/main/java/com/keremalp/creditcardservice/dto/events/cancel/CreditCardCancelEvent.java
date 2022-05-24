package com.keremalp.creditcardservice.dto.events.cancel;


import com.keremalp.creditcardservice.dto.products.CreateCreditCardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreditCardCancelEvent {
    private String transactionId;
    private CreateCreditCardDto account;
    private Date date;
}
