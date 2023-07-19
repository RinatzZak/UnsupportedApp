package com.rinattzak.userservice.controller.impl;

import com.rinattzak.userservice.controller.ContactController;
import com.rinattzak.userservice.dto.ContactDto;
import com.rinattzak.userservice.dto.ContactRequestDto;
import com.rinattzak.userservice.mapper.ContactMapper;
import com.rinattzak.userservice.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ContactControllerImplApi implements ContactController {

    private final ContactService service;
    private final ContactMapper mapper;

    @Override
    public ContactDto getContact(Long userId) {
        log.info("get contact of user with id: {}", userId);
        ContactDto contactDto = mapper.toDto(service.getContact(userId));
        log.info("return contact for user with id: {}", userId);
        return contactDto;
    }

    @Override
    public void createContact(Long userId, ContactRequestDto contactRequestDto) {
        log.info("create contact of user with id: {}", userId);
       // service.createContacts(userId, contactRequestDto);
        log.info("create contact for user with id: {}", userId);
    }
}
