package com.keremalp.managementservice.dto.products.flex_acc;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateFlexibleAccountDto {
    private Long id;
    private Long customerNumber;
    private Long accountNumber;
    private String customerName;
    private String address;
    private String phoneNumber;
    private Long  accountLimit;
}
