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
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardInfoDto that = (CardInfoDto) o;
        return Objects.equals(message, that.message) && Objects.equals(numberCard, that.numberCard) && Objects.equals(nameOfOwner, that.nameOfOwner) && cardType == that.cardType && Objects.equals(openingDate, that.openingDate) && Objects.equals(isBlocked, that.isBlocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, numberCard, nameOfOwner, cardType, openingDate, isBlocked);
    }
}
