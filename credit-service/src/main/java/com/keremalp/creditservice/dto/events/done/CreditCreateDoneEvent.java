package com.keremalp.creditservice.dto.events.done;


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
public class CreditCreateDoneEvent {

    private String transactionId;
    private CreateCreditDto credit;
    private Date date;
}
