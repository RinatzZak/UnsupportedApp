package com.rinattzak.loginfoservice.mapper;

import com.rinattzak.cardservice.dto.CardInfoDto;
import com.rinattzak.loginfoservice.entity.CardInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardInfoMapper {
    CardInfo toEntity(CardInfoDto cardInfoDto);
}
