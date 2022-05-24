package com.keremalp.managementservice.dto.events.create;

import com.keremalp.managementservice.dto.products.account.CreateCifOpenForAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateAccountForNotExistCustomerEvent {

    private String transactionId;
    private CreateCifOpenForAccountDto customer;
    private Date date;
}