package com.example.orderservice.entity;

import lombok.Data;

@Data
public class Book {
    private Integer id;

    private Author author;

    private String title;

    private Integer publishedIn;

    private Language language;
}
