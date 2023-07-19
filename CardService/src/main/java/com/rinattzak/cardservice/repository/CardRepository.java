package com.rinattzak.cardservice.repository;

import com.rinattzak.cardservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card getCardByUserIdAndNumberCard(Long userId, String cardNumber);
    List<Card> getAllByUserId(Long userId);
}
