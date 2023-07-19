package com.rinattzak.userservice.controller;

import com.rinattzak.userservice.dto.ContactDto;
import com.rinattzak.userservice.dto.ContactRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("api/v1")
@Tag(name = "Contact controller")
public interface ContactController {

    @Operation(
        operationId = "getContact",
        summary = "Endpoint for getting contact",
        tags = {"Contact controller"},
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ContactDto.class,
                    description = "dto information for contact"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
        }
    )

    @GetMapping(value = "/get-contact")
    ContactDto getContact(@RequestParam Long userId);

    @Operation(
        operationId = "createContact",
        summary = "Endpoint for create contact",
        tags = {"Contact controller"},
        responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = ContactDto.class,
                    description = "create  contact"))
            }),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content)
        }
    )

    @GetMapping(value = "/create-contact")
    void createContact(@RequestParam Long userId, @RequestBody ContactRequestDto contactRequestDto);
}
