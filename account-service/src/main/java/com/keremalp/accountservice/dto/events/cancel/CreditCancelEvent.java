package com.keremalp.accountservice.dto.events.cancel;



import com.keremalp.accountservice.dto.product.CreateAccountRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreditCancelEvent {
    private String transactionId;
    private CreateAccountRequestDto account;
    private Date date;
}
