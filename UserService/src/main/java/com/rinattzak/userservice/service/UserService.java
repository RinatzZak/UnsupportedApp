package com.rinattzak.userservice.service;

import com.rinattzak.userservice.dto.UserRequestDto;
import com.rinattzak.userservice.entity.User;

import java.util.List;

public interface UserService {
    void save(UserRequestDto userRequestDto);

    void update(UserRequestDto userRequestDto);
    void delete(Long userId);

    User getUser(Long id);

    List<User> getListOfUsers();
}
