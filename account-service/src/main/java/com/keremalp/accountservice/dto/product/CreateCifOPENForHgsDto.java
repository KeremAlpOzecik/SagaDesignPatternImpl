package com.keremalp.accountservice.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCifOPENForHgsDto {
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