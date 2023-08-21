package com.example.orderservice.entity;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;

@Data
public class Author {
    private int id;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "yy-MM-YYYY")
    private Date dateOfBirth;
    @Pattern(regexp = "YYYY")
    private Date yearOfBirth;
    private int distinguished;
}
