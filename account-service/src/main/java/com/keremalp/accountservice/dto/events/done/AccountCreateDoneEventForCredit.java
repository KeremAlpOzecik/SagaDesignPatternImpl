package com.keremalp.accountservice.dto.events.done;

import com.keremalp.accountservice.dto.product.CreateAccountForCreditRequestDto;
import com.keremalp.accountservice.dto.product.CreateAccountRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AccountCreateDoneEventForCredit {

    private String transactionId;
    private CreateAccountForCreditRequestDto account;
    private Date date;
}
