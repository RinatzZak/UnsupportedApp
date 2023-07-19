package com.rinattzak.cardservice.mapper;


import com.rinattzak.cardservice.dto.CardRequestDto;
import com.rinattzak.cardservice.dto.CardResponseDto;
import com.rinattzak.cardservice.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {
    Card toEntity(CardRequestDto cardRequestDto);
    CardResponseDto toDto(Card card);

    List<CardResponseDto> toListWithDto(List<Card> cards);
}
