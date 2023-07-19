package com.rinattzak.cardservice.controller;

import com.rinattzak.cardservice.dto.CardRequestDto;
import com.rinattzak.cardservice.dto.CardResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("api/v1")
@Tag(name = "Card controller")
public interface CardController {

    @Operation(
        operationId = "getCard",
        summary = "Endpoint for getting card",
        tags = {"Card controller"},
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CardResponseDto.class,
                    description = "dto information for card"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
        }
    )

    @GetMapping(value = "/get-card")
    CardResponseDto getCard(@RequestParam Long userId, @RequestParam String cardNumber);

    @Operation(
        operationId = "getListOfCards",
        summary = "Endpoint for getting cards",
        tags = {"Card controller"},
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = CardResponseDto.class,
                    description = "dto information for cards"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
        }
    )

    @GetMapping(value = "/get-all-card")
    List<CardResponseDto> getListOfCards(@RequestParam Long userId);

    @Operation(
        operationId = "createCard",
        summary = "Endpoint for save card",
        tags = {"Card controller"},
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
        }
    )

    @PostMapping(value = "/create-card")
    CardResponseDto save(CardRequestDto cardRequestDto);

    @Operation(
        operationId = "blockedCard",
        summary = "Endpoint for blocking card",
        tags = {"Card controller"},
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
        }
    )

    @PatchMapping(value = "/block-card")
    void blockingCard(Long userId, String cardNumber);
}
