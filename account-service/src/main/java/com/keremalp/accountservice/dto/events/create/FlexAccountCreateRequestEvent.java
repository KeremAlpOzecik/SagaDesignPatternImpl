package com.keremalp.accountservice.dto.events.create;

import com.keremalp.accountservice.dto.product.CreateCifOPENForFlexibleAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FlexAccountCreateRequestEvent {
    private String transactionId;
    private CreateCifOPENForFlexibleAccountDto customer;
    private Date date;
}