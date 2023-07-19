package com.rinattzak.userservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Min(0)
    @Max(120)
    private Integer age;
    @Email
    @NotNull
    @Pattern(regexp = "^(?!.*\\.\\.)[^\\s\\-.А-Яа-я][\\w.\\-]{4,29}@[\\w\\-]{3,15}\\.[a-zA-Z]{2,3}$")
    private String email;
    private ContactRequestDto contactRequestDto;
}
