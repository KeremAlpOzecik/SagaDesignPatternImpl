package com.keremalp.accountservice.dto.events.done;

import com.keremalp.accountservice.dto.product.CreateAccountForCreditCardRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AccountCreateDoneEventForCreditCard {

    private String transactionId;
    private CreateAccountForCreditCardRequestDto account;
    private Date date;
}
