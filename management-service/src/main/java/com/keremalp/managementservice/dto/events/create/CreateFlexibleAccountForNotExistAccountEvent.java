package com.keremalp.managementservice.dto.events.create;

import com.keremalp.managementservice.dto.products.flex_acc.CreateAccountForFlexibleAccountDto;
import com.keremalp.managementservice.dto.products.flex_acc.CreateCifOpenForFlexibleAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateFlexibleAccountForNotExistAccountEvent {
    private String transactionId;
    private CreateAccountForFlexibleAccountDto customer;
    private Date date;
}
