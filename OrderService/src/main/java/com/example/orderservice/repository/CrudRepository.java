package com.example.orderservice.repository;

import org.jooq.Condition;

import java.util.List;

public interface CrudRepository<T> {

    Integer SUCCESS_CODE = 1;
    T insert(T t);
    T update(T t);
    T find(Integer id);
    List<T> findAll();
    Boolean delete(Integer id);
}