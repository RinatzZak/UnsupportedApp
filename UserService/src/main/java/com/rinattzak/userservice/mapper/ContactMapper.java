package com.rinattzak.userservice.mapper;

import com.rinattzak.userservice.dto.ContactDto;
import com.rinattzak.userservice.dto.ContactRequestDto;
import com.rinattzak.userservice.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactMapper {
    Contact toEntity(ContactRequestDto contactRequestDto);
    ContactDto toDto(Contact contact);
}
