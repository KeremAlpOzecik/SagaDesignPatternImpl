package com.keremalp.managementservice.dto.products.credit;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccountForCreditDto {
    private Long id;
    private Long customerNumber;
    private Long accountNumber;
    private String creditNumber;
    private Long creditAmount;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String address;
    private String phoneNumber;
}
