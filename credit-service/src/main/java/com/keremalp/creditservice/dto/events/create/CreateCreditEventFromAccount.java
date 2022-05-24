package com.keremalp.creditservice.dto.events.create;


import com.keremalp.creditservice.dto.product.CreateAccountRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateCreditEventFromAccount {

    private String transactionId;
    private CreateAccountRequestDto account;
    private Date date;
}
