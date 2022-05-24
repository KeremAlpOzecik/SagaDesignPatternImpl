package com.keremalp.creditcardservice.converter;

import com.keremalp.creditcardservice.dto.products.CreateAccountRequestDto;
import com.keremalp.creditcardservice.dto.products.CreateCreditCardDto;
import com.keremalp.creditcardservice.entity.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditCardMapper {
    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    CreateCreditCardDto creditCardToCreditCardDto(CreditCard creditCard);
    CreditCard creditCardDtoToCreditCard(CreateCreditCardDto createCreditCardDto);
    CreateAccountRequestDto afasfasT(CreditCard ads);

    CreateCreditCardDto denemeTodeneme(CreateCreditCardDto c);




}
