package com.keremalp.managementservice.dto.events.done;

import com.keremalp.managementservice.dto.products.credit.CreateCreditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreditCreateDone {

    private String transactionId;
    private CreateCreditDto credit;
    private Date date;
}