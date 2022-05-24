package com.keremalp.managementservice.dto.events.create;

import com.keremalp.managementservice.dto.products.credit.CreateCifOpenForCreditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateCreditForNotExistCustomerEvent {

    private String transactionId;
    private CreateCifOpenForCreditDto customer;
    private Date date;
}