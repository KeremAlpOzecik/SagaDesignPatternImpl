package com.keremalp.creditcardservice.dto.events.create;


import com.keremalp.creditcardservice.dto.products.CreateAccountRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateCreditCardEventFromAccount {

    private String transactionId;
    private CreateAccountRequestDto account;
    private Date date;
}
