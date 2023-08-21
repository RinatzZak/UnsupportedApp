package com.rinattzak.loginfoservice.service.impl;

import com.rinattzak.loginfoservice.entity.CardInfo;
import com.rinattzak.loginfoservice.repository.CardInfoRepository;
import com.rinattzak.loginfoservice.service.CardInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardInfoServiceImpl implements CardInfoService {
    private final CardInfoRepository repository;

    @Override
    @KafkaListener(topics = {"info-topic"},
        groupId = "info-service",
        containerFactory = "singleFactory")
    public void save(@Payload CardInfo cardInfoDto) {
        log.info("Start save log for card with number {}", cardInfoDto.getNumberCard());
        repository.save(cardInfoDto);
    }
}
