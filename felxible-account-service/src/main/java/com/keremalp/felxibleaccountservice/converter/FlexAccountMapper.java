package com.keremalp.felxibleaccountservice.converter;



import com.keremalp.felxibleaccountservice.dto.product.CreateFlexibleAccountDto;
import com.keremalp.felxibleaccountservice.entity.FlexAcc;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlexAccountMapper {
    FlexAccountMapper INSTANCE = Mappers.getMapper(FlexAccountMapper.class);
    CreateFlexibleAccountDto accountToCreateFlexAccountRequestDto(FlexAcc flexAcc);
    FlexAcc createFlexAccountRequestDtoToFlexAccount(CreateFlexibleAccountDto createFlexibleAccountDto);
    FlexAcc ctoB(CreateFlexibleAccountDto createFlexibleAccountDto);





}
