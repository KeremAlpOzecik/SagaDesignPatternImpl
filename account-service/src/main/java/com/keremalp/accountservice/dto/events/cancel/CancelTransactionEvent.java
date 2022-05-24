package com.keremalp.accountservice.dto.events.cancel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelTransactionEvent {
    private String transactionId;
    private FlexAccountCancelEvent customer;
    private Date date;
}
