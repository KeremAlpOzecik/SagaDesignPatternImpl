package com.keremalp.customerservice.dto.events.create;

import com.keremalp.customerservice.dto.product.CreateCifOpenForFlexibleAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateFlexibleAccountForNotExistCustomerEvent {

    private String transactionId;
    private CreateCifOpenForFlexibleAccountDto customer;
    private Date date;
}