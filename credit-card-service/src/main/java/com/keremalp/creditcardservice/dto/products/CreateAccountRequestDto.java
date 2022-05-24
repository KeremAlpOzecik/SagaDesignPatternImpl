package com.keremalp.creditcardservice.dto.products;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccountRequestDto {
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String PhoneNumber;
    private String address;
    private Long customerNumber;
    private Long accountNumber;

}

