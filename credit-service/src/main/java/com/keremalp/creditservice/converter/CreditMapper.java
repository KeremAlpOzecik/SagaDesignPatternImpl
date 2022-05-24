package com.keremalp.creditservice.converter;

import com.keremalp.creditservice.dto.events.done.CreditCreateDoneEvent;
import com.keremalp.creditservice.dto.product.CreateCreditDto;
import com.keremalp.creditservice.entity.Credit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditMapper {
    CreditMapper INSTANCE = Mappers.getMapper(CreditMapper.class);
    CreateCreditDto creditToCreditDto(Credit flexAcc);
    Credit creditDtoToCredit(CreateCreditDto createFlexibleAccountDto);

    CreditCreateDoneEvent creditToCreditCreateDoneEvent(Credit flexAcc);
    Credit ccccTooo(CreateCreditDto crs);

    CreateCreditDto fdsfdsTtodaf(Credit fadsa);




}
