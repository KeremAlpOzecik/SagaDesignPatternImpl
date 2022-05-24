package com.keremalp.customerservice.converter;

import com.keremalp.customerservice.dto.product.CreateCifOpenForAccountDto;
import com.keremalp.customerservice.dto.product.CreateCifOpenForCreditDto;
import com.keremalp.customerservice.dto.product.CreateCifOpenForFlexibleAccountDto;
import com.keremalp.customerservice.dto.product.CreateCifOpenRequestDto;
import com.keremalp.customerservice.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    Customer createCifOpenRequestToCustomer(CreateCifOpenRequestDto createCifOpenRequestDto);
    CreateCifOpenRequestDto customerToCreateCifOpen(Customer customer);
    CreateCifOpenForAccountDto customerToCreateCifOpenForAccountDto(Customer customer);
    CreateCifOpenForFlexibleAccountDto customerToCreateCifOpenForFlexibleAccountDto(Customer customer);
    CreateCifOpenForFlexibleAccountDto cToFlex(Customer customer);
    Customer fdsfsadfsad(CreateCifOpenForCreditDto adfsad);

    Customer createCifOpenForCreditToCustomer(CreateCifOpenForCreditDto customer);
    CreateCifOpenForFlexibleAccountDto lalala(Customer csf);


}
