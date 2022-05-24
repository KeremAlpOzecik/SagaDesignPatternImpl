package com.keremalp.felxibleaccountservice.dto.events.done;



import com.keremalp.felxibleaccountservice.dto.product.CreateFlexibleAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FlexAccountCreateDoneEvent {

    private String transactionId;
    private CreateFlexibleAccountDto account;
    private Date date;
}
