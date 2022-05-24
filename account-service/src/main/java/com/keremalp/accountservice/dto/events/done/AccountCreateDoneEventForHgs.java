package com.keremalp.accountservice.dto.events.done;

import com.keremalp.accountservice.dto.product.CreateAccountForCreditCardRequestDto;
import com.keremalp.accountservice.dto.product.CreateAccountForHgsRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AccountCreateDoneEventForHgs {

    private String transactionId;
    private CreateAccountForHgsRequestDto account;
    private Date date;
}
