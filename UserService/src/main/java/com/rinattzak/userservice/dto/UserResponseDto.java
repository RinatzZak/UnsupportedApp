package com.rinattzak.userservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Min(0)
    @Max(120)
    private Integer age;
    @NotNull
    @Valid
    private ContactDto contactDto;
}
