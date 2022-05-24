package com.keremalp.managementservice.dto.events.create;

import com.keremalp.managementservice.dto.products.CreateNotificationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotificationEvent {
    private String transactionId;
    private CreateNotificationDto notify;
    private Date date;

}
