package com.rinattzak.userservice.service;

import com.rinattzak.userservice.dto.ContactRequestDto;
import com.rinattzak.userservice.dto.UserRequestDto;
import com.rinattzak.userservice.entity.Contact;
import com.rinattzak.userservice.entity.User;

public interface ContactService {
    void createContacts(Long userId, ContactRequestDto contactRequestDto);
    Contact getContact(Long userId);
}
