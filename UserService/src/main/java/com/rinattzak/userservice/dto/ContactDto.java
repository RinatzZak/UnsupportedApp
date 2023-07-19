package com.rinattzak.userservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    @NotNull
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    @Pattern(regexp = "^\\d{11}$")
    private String phoneNumber;
    @NotNull
    private String address;
}
