package com.rinattzak.cardservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rinattzak.cardservice.entity.enums.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardInfoDto {
    @NotNull
    private String message;
    @NotNull
    private String numberCard;
    @NotNull
    private String nameOfOwner;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @NotNull
    private LocalDate openingDate;
    @NotNull
    private Boolean isBlocked;
}
