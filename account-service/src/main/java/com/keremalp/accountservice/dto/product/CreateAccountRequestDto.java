package com.keremalp.accountservice.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccountRequestDto {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Long customerNumber;
    private Long accountNumber;
}

