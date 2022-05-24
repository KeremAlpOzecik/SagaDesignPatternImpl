package com.keremalp.hgsservice.converter;

import com.keremalp.hgsservice.dto.product.CreateHgsDto;
import com.keremalp.hgsservice.entity.Hgs;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.net.CacheRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HgsMapper {
    HgsMapper INSTANCE = Mappers.getMapper(HgsMapper.class);

    CreateHgsDto hgsToCreditDto(Hgs flexAcc);
    Hgs creditDtoToHgs(CreateHgsDto createFlexibleAccountDto);
    CreateHgsDto zottiri (CacheRequest fad);







}
