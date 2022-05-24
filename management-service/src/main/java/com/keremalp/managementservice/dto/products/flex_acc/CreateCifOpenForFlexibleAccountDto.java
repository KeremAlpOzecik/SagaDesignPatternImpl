package com.keremalp.managementservice.dto.products.flex_acc;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCifOpenForFlexibleAccountDto {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Long customerNumber;
}