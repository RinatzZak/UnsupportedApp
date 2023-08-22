package com.rinattzak.cardservice.service.impl;

import com.rinattzak.cardservice.dto.CardInfoDto;
import com.rinattzak.cardservice.dto.CardRequestDto;
import com.rinattzak.cardservice.dto.CardResponseDto;
import com.rinattzak.cardservice.entity.Card;
import com.rinattzak.cardservice.mapper.CardMapper;
import com.rinattzak.cardservice.repository.CardRepository;
import com.rinattzak.cardservice.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private static final Random random = new Random();
    private final CardRepository repository;
    private final CardMapper mapper;
    private final KafkaTemplate<String, CardInfoDto> kafkaTemplate;

    @Override
    @Transactional
    public CardResponseDto save(CardRequestDto cardRequestDto) {
        log.info("Start save card for user with id {}:", cardRequestDto.getUserId());
        Card card = mapper.toEntity(cardRequestDto);
        card.setNumberCard(generateCardNumber());
        card.setCCV(generateCvv());
        card.setPIN(generatePIN());
        card.setOpeningDate(LocalDate.now());
        card.setIsBlocked(false);
        repository.save(card);
        log.info("Saved card for user with id {}:", cardRequestDto.getUserId());
        log.info("Start send card info to Kafka topic, card number - {}", card.getNumberCard());
        CardInfoDto cardInfoDto = mapper.toInfoDto(card);
        cardInfoDto.setMessage("Карта для пользователя - " + cardRequestDto.getUserId());
        kafkaTemplate.setTransactionIdPrefix("cardInfoMessage");
        CompletableFuture<SendResult<String, CardInfoDto>> future =
            kafkaTemplate.executeInTransaction(operations ->
                operations.send("info-topic", cardInfoDto));
        assert future != null;
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Message successful send!");
                log.info(String.valueOf(kafkaTemplate.inTransaction()));
                log.info(kafkaTemplate.getTransactionIdPrefix());
            } else {
                log.info("Failed to send message to topic - {}", ex.getMessage());
            }
        });


        return mapper.toDto(card);
    }

    @Override
    public void blockCard(Long userId, String cardNumber) {
        log.info("Start block card for user with id {}:", userId);
        Card card = repository.getCardByUserIdAndNumberCard(userId, cardNumber);
        card.setIsBlocked(true);
        repository.save(card);
        log.info("Finished block card for user with id {}:", userId);
    }

    @Override
    public List<CardResponseDto> getListOfCards(Long userId) {
        log.info("Start getting cards for user with id {}:", userId);
        return mapper.toListWithDto(repository.getAllByUserId(userId));
    }

    @Override
    public CardResponseDto getCard(Long userId, String cardNumber) {
        log.info("Start get card for user with id {} and cardNumber {}:", userId, cardNumber);
        return mapper.toDto(repository.getCardByUserIdAndNumberCard(userId, cardNumber));
    }

    private String generateRandomNumber(int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    private String generateCardNumber() {
        return generateRandomNumber(16);
    }

    private String generateCvv() {
        return generateRandomNumber(3);
    }

    private String generatePIN() {
        return generateRandomNumber(4);
    }
}
