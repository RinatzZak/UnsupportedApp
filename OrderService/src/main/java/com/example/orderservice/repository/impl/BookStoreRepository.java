package com.example.orderservice.repository.impl;

import com.example.orderservice.entity.BookStore;
import com.example.orderservice.repository.CrudRepository;
import com.example.orderservice.util.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.zip.DataFormatException;

@Repository
@RequiredArgsConstructor
public class BookStoreRepository implements CrudRepository<BookStore> {

    private final DSLContext context;

    @SneakyThrows
    @Override
    public BookStore insert(BookStore bookStore) {
        return context.insertInto(com.example.orderservice.tables.BookStore.BOOK_STORE)
            .set(context.newRecord(com.example.orderservice.tables.BookStore.BOOK_STORE, bookStore))
            .returning()
            .fetchOptional()
            .orElseThrow(DataFormatException::new)
            .into(BookStore.class);
    }

    @SneakyThrows
    @Override
    public BookStore update(BookStore bookStore) {
        return context.update(com.example.orderservice.tables.BookStore.BOOK_STORE)
            .set(context.newRecord(com.example.orderservice.tables.BookStore.BOOK_STORE, bookStore))
            .where(com.example.orderservice.tables.BookStore.BOOK_STORE.ID.eq(bookStore.getId()))
            .returning()
            .fetchOptional()
            .orElseThrow(DataFormatException::new)
            .into(BookStore.class);
    }

    @Override
    public BookStore find(Integer id) {
        return context.selectFrom(com.example.orderservice.tables.BookStore.BOOK_STORE)
            .where(com.example.orderservice.tables.BookStore.BOOK_STORE.ID.eq(id))
            .fetchOptional()
            .orElseThrow(EntityNotFoundException::new)
            .into(BookStore.class);
    }

    @Override
    public List<BookStore> findAll() {
        return context.selectFrom(com.example.orderservice.tables.BookStore.BOOK_STORE)
            .fetch()
            .into(BookStore.class);
    }

    @Override
    public Boolean delete(Integer id) {
        return context.delete(com.example.orderservice.tables.BookStore.BOOK_STORE)
            .where(com.example.orderservice.tables.BookStore.BOOK_STORE.ID.eq(id))
            .execute() == SUCCESS_CODE;
    }
}
