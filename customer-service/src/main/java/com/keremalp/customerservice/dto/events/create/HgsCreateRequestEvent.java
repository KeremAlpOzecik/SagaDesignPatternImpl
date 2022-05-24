package com.keremalp.customerservice.dto.events.create;

import com.keremalp.customerservice.dto.product.CreateCifOpenForHgsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class HgsCreateRequestEvent {
    private String transactionId;
    private CreateCifOpenForHgsDto customer;
    private Date date;
}