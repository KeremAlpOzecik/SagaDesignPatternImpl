package com.keremalp.hgsservice.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccountRequestDto {
    private Long id;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String phoneNumber;
    private String address;
    private Long customerNumber;
    private Long accountNumber;
    private String carPlate;

}

