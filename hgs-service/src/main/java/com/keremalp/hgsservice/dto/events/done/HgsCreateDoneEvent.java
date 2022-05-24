package com.keremalp.hgsservice.dto.events.done;


import com.keremalp.hgsservice.dto.product.CreateHgsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class HgsCreateDoneEvent {

    private String transactionId;
    private CreateHgsDto hgs;
    private Date date;
}
