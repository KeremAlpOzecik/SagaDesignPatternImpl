package com.keremalp.accountservice.dto.events.create;

import com.keremalp.accountservice.dto.product.CreateCifOPENForHgsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class HgsRequestEvent {
    private String transactionId;
    private CreateCifOPENForHgsDto customer;
    private Date date;
}