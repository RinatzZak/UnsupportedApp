package com.rinattzak.userservice.mapper;

import com.rinattzak.userservice.dto.UserRequestDto;
import com.rinattzak.userservice.dto.UserResponseDto;
import com.rinattzak.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toEntity(UserRequestDto userRequestDto);
    UserResponseDto toDto(User user);
    List<UserResponseDto> toListWithDto(List<User> users);
}
