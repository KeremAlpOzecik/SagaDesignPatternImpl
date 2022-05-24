package com.keremalp.accountservice.converter;

import com.keremalp.accountservice.dto.product.CreateAccountForCreditCardRequestDto;
import com.keremalp.accountservice.dto.product.CreateAccountForCreditRequestDto;
import com.keremalp.accountservice.dto.product.CreateAccountForHgsRequestDto;
import com.keremalp.accountservice.dto.product.CreateAccountRequestDto;
import com.keremalp.accountservice.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account createAccountOPENRequestToAccount(CreateAccountRequestDto createAccountRequestDto);

    Account createAccountForCreditToAccount(CreateAccountForCreditRequestDto createAccountForCreditRequestDto);

    CreateAccountRequestDto accountToCreateAccountRequestDto(Account account);

    Account createAccountForCreditCardToAccount(CreateAccountForCreditCardRequestDto createAccountForCreditCardRequestDto);
    Account createAccountForHgsToAccount(CreateAccountForHgsRequestDto createAccountForHgsRequestDto);
}
