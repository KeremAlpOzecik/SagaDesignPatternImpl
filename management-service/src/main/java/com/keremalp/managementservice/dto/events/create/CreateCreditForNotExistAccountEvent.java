package com.keremalp.managementservice.dto.events.create;

import com.keremalp.managementservice.dto.products.credit.CreateAccountForCreditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateCreditForNotExistAccountEvent {
    private String transactionId;
    private CreateAccountForCreditDto customer;
    private Date date;
}