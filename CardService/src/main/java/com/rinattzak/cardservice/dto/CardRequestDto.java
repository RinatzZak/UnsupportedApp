package com.rinattzak.cardservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rinattzak.cardservice.entity.enums.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {

    @NotNull
    private Long userId;
    @NotNull
    private String nameOfOwner;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @NotNull
    @DecimalMin("0.00")
    @DecimalMax("0000000000000000000.00")
    private BigDecimal balance;
}
