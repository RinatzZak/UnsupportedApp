package com.rinattzak.loginfoservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cardlogs")
public class CardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String message;
    @NotNull
    private String numberCard;
    @NotNull
    private String nameOfOwner;
    @NotNull
    private String cardType;
    @NotNull
    @JsonFormat(pattern = "MM/YY")
    private LocalDate openingDate;
    @NotNull
    private Boolean isBlocked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardInfo cardInfo = (CardInfo) o;
        return Objects.equals(id, cardInfo.id) && Objects.equals(message, cardInfo.message) && Objects.equals(numberCard, cardInfo.numberCard) && Objects.equals(nameOfOwner, cardInfo.nameOfOwner) && Objects.equals(cardType, cardInfo.cardType) && Objects.equals(openingDate, cardInfo.openingDate) && Objects.equals(isBlocked, cardInfo.isBlocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, numberCard, nameOfOwner, cardType, openingDate, isBlocked);
    }
}
