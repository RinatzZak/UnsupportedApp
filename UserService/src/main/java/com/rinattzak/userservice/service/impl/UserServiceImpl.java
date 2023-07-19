package com.rinattzak.userservice.service.impl;

import com.rinattzak.userservice.dto.UserRequestDto;
import com.rinattzak.userservice.entity.Contact;
import com.rinattzak.userservice.entity.User;
import com.rinattzak.userservice.mapper.ContactMapper;
import com.rinattzak.userservice.mapper.UserMapper;
import com.rinattzak.userservice.repository.ContactRepository;
import com.rinattzak.userservice.repository.UserRepository;
import com.rinattzak.userservice.service.ContactService;
import com.rinattzak.userservice.service.UserService;
import com.rinattzak.userservice.util.exception.PasswordIncorrectException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ContactRepository contactRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final ContactMapper contactMapper;


    @Override
    public void save(UserRequestDto userRequestDto) {
        log.info("save user : {}", userRequestDto.getFirstName());
        User user = userMapper.toEntity(userRequestDto);
        user.setPassword(encoder.encode(userRequestDto.getPassword()));
        User savedUser = repository.save(user);

        Contact contact = contactMapper.toEntity(userRequestDto.getContactRequestDto());
        contact.setUser(savedUser);
        contactRepository.save(contact);

        repository.save(savedUser);
        log.info("saved user: {}", user);
    }

    @Override
    public void update(UserRequestDto user) {
        log.info("start update user this email: {}", user.getEmail());
        User user1 = userMapper.toEntity(user);
        Optional<User> current = repository.findById(user1.getId());
        if (user.getPassword().isEmpty()) {
            log.error("Incorrect password for clientId: {}", current.get().getId());
            throw new PasswordIncorrectException();
        } else {
            current.get().setPassword(encoder.encode(user.getPassword()));
            current.get().setAge(user.getAge());
            current.get().setFirstName(user.getFirstName());
            current.get().setLastName(user.getLastName());
            repository.save(current.get());
            log.info("updated user with id: {}", user1.getId());
        }
    }

    @Override
    public void delete(Long userId) {
        log.info("delete user with id: {}", userId);
        repository.deleteById(Math.toIntExact(userId));
    }

    @Override
    public User getUser(Long id) {
        log.info("getting user with id: {}", id);
        Contact contact = contactRepository.findByUserId(id).get();
        User user = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return user;
    }

    @Override
    public List<User> getListOfUsers() {
        log.info("get list of users");
        return repository.findAll();
    }
}
