package com.keremalp.managementservice.dto.products.cif;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCifOpenRequestDto {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Long customerNumber;
}
