package com.keremalp.creditservice.dto.events.cancel;


import com.keremalp.creditservice.dto.product.CreateCreditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreditCancelEvent {
    private String transactionId;
    private CreateCreditDto account;
    private Date date;
}
