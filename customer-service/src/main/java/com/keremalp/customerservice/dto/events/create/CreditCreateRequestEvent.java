package com.keremalp.customerservice.dto.events.create;

import com.keremalp.customerservice.dto.product.CreateCifOpenForCreditDto;
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
    private CreateCifOpenForCreditDto customer;
    private Date date;
}