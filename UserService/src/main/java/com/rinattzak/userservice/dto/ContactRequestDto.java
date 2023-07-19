package com.rinattzak.userservice.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDto {

    @Pattern(regexp = "^\\d{11}$")
    private String phoneNumber;
    private String address;
}
