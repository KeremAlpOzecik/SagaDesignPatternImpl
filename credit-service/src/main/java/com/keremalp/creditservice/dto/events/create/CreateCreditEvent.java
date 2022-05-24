package com.keremalp.creditservice.dto.events.create;


import com.keremalp.creditservice.dto.product.CreateCreditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateCreditEvent {

    private String transactionId;
    private CreateCreditDto createCreditDto;
    private Date date;
}
