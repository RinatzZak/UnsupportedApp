package com.rinattzak.userservice.service.impl;

import com.rinattzak.userservice.dto.ContactRequestDto;
import com.rinattzak.userservice.dto.UserRequestDto;
import com.rinattzak.userservice.entity.Contact;
import com.rinattzak.userservice.entity.User;
import com.rinattzak.userservice.mapper.ContactMapper;
import com.rinattzak.userservice.mapper.UserMapper;
import com.rinattzak.userservice.repository.ContactRepository;
import com.rinattzak.userservice.repository.UserRepository;
import com.rinattzak.userservice.service.ContactService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository repository;
    private final UserRepository userRepository;
    private final ContactMapper mapper;
    private final UserMapper userMapper;

    @Override
    public void createContacts(Long userId, ContactRequestDto contactRequestDto) {
        log.info("start create contacts for user with id: {}", userId);
        Contact contact = mapper.toEntity(contactRequestDto);
        contact.setUser(userRepository.findById(userId).get());
        repository.save(contact);
        log.info("created contacts for user with id: {}", userId);
    }

    @Override
    public Contact getContact(Long userId) {
        log.info("start find contacts for user with id: {}", userId);
        Optional<User> user = userRepository.findById(userId);
        Contact contact = repository.findByUserId(userId).get();
        log.info("returning find contacts for user with id: {}", userId);
        return contact;
    }
}
