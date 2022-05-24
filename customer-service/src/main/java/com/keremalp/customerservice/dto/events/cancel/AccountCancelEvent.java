package com.keremalp.customerservice.dto.events.cancel;


import com.keremalp.customerservice.dto.product.CreateCifOpenForAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AccountCancelEvent {
    private String transactionId;
    private CreateCifOpenForAccountDto account;
    private Date date;
}
