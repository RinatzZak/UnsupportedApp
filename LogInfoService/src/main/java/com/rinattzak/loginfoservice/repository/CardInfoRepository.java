package com.rinattzak.loginfoservice.repository;

import com.rinattzak.loginfoservice.entity.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardInfoRepository extends JpaRepository<CardInfo, Long> {
}
