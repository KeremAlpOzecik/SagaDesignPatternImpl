package com.keremalp.managementservice.dto.events.cancel;

import com.keremalp.managementservice.dto.products.cif.CreateCifOpenRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateCIFCancel {
    private String transactionId;
    private CreateCifOpenRequestDto customer;
    private Date date;
}
