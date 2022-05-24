package com.keremalp.managementservice.dto.products.hgs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccountForHgsDto {
    private Long id;
    private Long customerNumber;
    private Long accountNumber;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String address;
    private String phoneNumber;
    private String carPlate;
}
