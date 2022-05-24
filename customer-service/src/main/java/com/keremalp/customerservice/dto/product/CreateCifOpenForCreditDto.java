package com.keremalp.customerservice.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCifOpenForCreditDto {
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