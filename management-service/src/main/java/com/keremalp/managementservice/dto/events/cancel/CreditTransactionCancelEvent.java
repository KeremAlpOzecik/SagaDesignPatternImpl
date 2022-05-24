package com.keremalp.managementservice.dto.events.cancel;




import com.keremalp.managementservice.dto.products.credit.CreateCreditDto;
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
public class CreditTransactionCancelEvent {
    private String transactionId;
    private CreateCreditDto creditDto;
    private Date date;
}
