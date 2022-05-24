package com.keremalp.managementservice.dto.events.done;

import com.keremalp.managementservice.dto.products.hgs.CreateHgsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class HgsCreateDone {

    private String transactionId;
    private CreateHgsDto hgs;
    private Date date;
}