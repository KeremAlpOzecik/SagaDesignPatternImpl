package com.keremalp.managementservice.dto.events.create;

import com.keremalp.managementservice.dto.products.hgs.CreateCifOpenForHgsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateHgsForNotExistCustomerEvent {

    private String transactionId;
    private CreateCifOpenForHgsDto customer;
    private Date date;
}