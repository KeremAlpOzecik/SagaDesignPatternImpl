package com.keremalp.accountservice.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCifOPENRequestDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private Long customerNumber;


}
