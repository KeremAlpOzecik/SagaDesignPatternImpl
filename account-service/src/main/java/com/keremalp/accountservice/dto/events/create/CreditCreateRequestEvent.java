package com.keremalp.accountservice.dto.events.create;

import com.keremalp.accountservice.dto.product.CreateCifOPENForCreditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreditCreateRequestEvent {
    private String transactionId;
    private CreateCifOPENForCreditDto customer;
    private Date date;
}