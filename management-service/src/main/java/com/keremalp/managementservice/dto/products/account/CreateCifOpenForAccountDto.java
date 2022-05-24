package com.keremalp.managementservice.dto.products.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCifOpenForAccountDto {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Long customerNumber;
}