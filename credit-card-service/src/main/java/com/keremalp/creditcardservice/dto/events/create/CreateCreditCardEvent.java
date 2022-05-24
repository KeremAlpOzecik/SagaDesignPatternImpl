package com.keremalp.creditcardservice.dto.events.create;


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
public class CreateCreditCardEvent {

    private String transactionId;
    private CreateCreditCardDto createCreditDto;
    private Date date;
}
