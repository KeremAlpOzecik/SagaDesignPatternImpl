package com.keremalp.customerservice.dto.events.done;

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
public class CifOpenCreatedDoneEvent {
    private String transactionId;
    private CreateCifOpenRequestDto customer;
    private Date date;
}
