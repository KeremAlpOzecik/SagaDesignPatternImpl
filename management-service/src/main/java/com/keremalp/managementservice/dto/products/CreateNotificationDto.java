package com.keremalp.managementservice.dto.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class CreateNotificationDto {
    private String email;
    private String contract;
    private LocalDateTime localDateTime;
}
