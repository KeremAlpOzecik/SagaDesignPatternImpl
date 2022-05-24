package com.keremalp.hgsservice.dto.events.create;


import com.keremalp.hgsservice.dto.product.CreateAccountRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CreateHgsEventFromAccount {

    private String transactionId;
    private CreateAccountRequestDto account;
    private Date date;
}
