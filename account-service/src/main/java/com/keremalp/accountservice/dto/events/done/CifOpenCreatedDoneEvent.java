package com.keremalp.accountservice.dto.events.done;

import com.keremalp.accountservice.dto.product.CreateCifOPENRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CifOpenCreatedDoneEvent {
    private String transactionId;
    private CreateCifOPENRequestDto customer;
    private Date date;
}
