package com.keremalp.managementservice.dto.events.cancel;




import com.keremalp.managementservice.dto.products.flex_acc.CreateFlexibleAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FlexAccountTransactionCancelEvent {
    private String transactionId;
    private CreateFlexibleAccountDto account;
    private Date date;
}
