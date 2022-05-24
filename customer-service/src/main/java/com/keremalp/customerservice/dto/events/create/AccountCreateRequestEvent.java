package com.keremalp.customerservice.dto.events.create;

import com.keremalp.customerservice.dto.product.CreateCifOpenRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AccountCreateRequestEvent {
    private String transactionId;
    private CreateCifOpenRequestDto customer;
    private Date date;
}