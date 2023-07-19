package com.rinattzak.cardservice.controller.impl;

import com.rinattzak.cardservice.controller.CardController;
import com.rinattzak.cardservice.dto.CardRequestDto;
import com.rinattzak.cardservice.dto.CardResponseDto;
import com.rinattzak.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CardControllerImplApi implements CardController {
    private final CardService cardService;
    @Override
    public CardResponseDto getCard(Long userId, String cardNumber) {
        log.info("Request for get card for user with id {}: ", userId);
        CardResponseDto card = cardService.getCard(userId, cardNumber);
        log.info("Return card for user with id {}:", userId);
        return card;
    }

    @Override
    public List<CardResponseDto> getListOfCards(Long userId) {
        log.info("Request for get cards for user with id {}: ", userId);
        List<CardResponseDto> cards = cardService.getListOfCards(userId);
        log.info("Return card for user with id {}:", userId);
        return cards;
    }

    @Override
    public CardResponseDto save(CardRequestDto cardRequestDto) {
        log.info("Request for save card for user with id {}: ", cardRequestDto.getUserId());
        CardResponseDto cardResponseDto = cardService.save(cardRequestDto);
        log.info("Save card for user with id {}:", cardRequestDto.getUserId());
        return cardResponseDto;
    }

    @Override
    public void blockingCard(Long userId, String cardNumber) {
        log.info("Request for block card for user with id {}: ", userId);
        cardService.blockCard(userId, cardNumber);
        log.info("Blocked card for user with id {}:", userId);
    }
}
