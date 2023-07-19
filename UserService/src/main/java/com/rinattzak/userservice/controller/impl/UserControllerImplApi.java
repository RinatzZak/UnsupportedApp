package com.rinattzak.userservice.controller.impl;

import com.rinattzak.userservice.controller.UserController;
import com.rinattzak.userservice.dto.ContactDto;
import com.rinattzak.userservice.dto.UserRequestDto;
import com.rinattzak.userservice.dto.UserResponseDto;
import com.rinattzak.userservice.entity.User;
import com.rinattzak.userservice.mapper.ContactMapper;
import com.rinattzak.userservice.mapper.UserMapper;
import com.rinattzak.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class UserControllerImplApi implements UserController {
    private final UserService service;
    private final UserMapper mapper;
    private final ContactMapper contactMapper;

    @Override
    public ResponseEntity<Void> registration(UserRequestDto userRequestDto) {
        service.save(userRequestDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> delete(Long userId) {
        service.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> update(UserRequestDto userRequestDto) {
        service.update(userRequestDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserResponseDto> getInfo(Long userId) {
        User user = service.getUser(userId);
        UserResponseDto userResponseDto = mapper.toDto(user);
        return ResponseEntity.ok(userResponseDto);
    }

    @Override
    public ResponseEntity<List<UserResponseDto>> getListOfUsers() {
        return ResponseEntity.ok(mapper.toListWithDto(service.getListOfUsers()));
    }
}
