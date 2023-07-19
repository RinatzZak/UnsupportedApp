package com.rinattzak.cardservice.service;

import com.rinattzak.cardservice.dto.CardRequestDto;
import com.rinattzak.cardservice.dto.CardResponseDto;

import java.util.List;

public interface CardService {

    CardResponseDto save(CardRequestDto cardRequestDto);
    void blockCard(Long userId, String cardNumber);

    List<CardResponseDto> getListOfCards(Long userId);

    CardResponseDto getCard(Long userId, String cardNumber);
}
