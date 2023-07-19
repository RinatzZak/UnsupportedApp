package com.rinattzak.loginfoservice.service.impl;

import com.rinattzak.cardservice.dto.CardInfoDto;
import com.rinattzak.loginfoservice.mapper.CardInfoMapper;
import com.rinattzak.loginfoservice.repository.CardInfoRepository;
import com.rinattzak.loginfoservice.service.CardInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardInfoServiceImpl implements CardInfoService {
    private final CardInfoRepository repository;
    private final CardInfoMapper mapper;

    @Override
    @KafkaListener(topics = {"info-topic"},
        groupId = "info-service",
        containerFactory = "singleFactory")
    public void save(CardInfoDto cardInfoDto) {
        log.info("Start save log for card with number {}", cardInfoDto.getNumberCard());
        repository.save(mapper.toEntity(cardInfoDto));
    }
}
