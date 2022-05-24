package com.keremalp.creditcardservice.dto.events.done;


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
public class CreditCardCreateDoneEvent {

    private String transactionId;
    private CreateCreditCardDto creditCard;
    private Date date;
}
