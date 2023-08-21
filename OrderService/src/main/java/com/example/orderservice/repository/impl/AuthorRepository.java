package com.example.orderservice.repository.impl;

import com.example.orderservice.entity.Author;
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
public class AuthorRepository implements CrudRepository<Author> {

    private final DSLContext context;

    @SneakyThrows
    @Override
    public Author insert(Author author) {
        return context.insertInto(com.example.orderservice.tables.Author.AUTHOR)
            .set(context.newRecord(com.example.orderservice.tables.Author.AUTHOR, author))
            .returning()
            .fetchOptional()
            .orElseThrow(DataFormatException::new)
            .into(Author.class);
    }

    @SneakyThrows
    @Override
    public Author update(Author author) {
        return context.update(com.example.orderservice.tables.Author.AUTHOR)
            .set(context.newRecord(com.example.orderservice.tables.Author.AUTHOR, author))
            .where(com.example.orderservice.tables.Author.AUTHOR.ID.eq(author.getId()))
            .returning()
            .fetchOptional()
            .orElseThrow(DataFormatException::new)
            .into(Author.class);
    }

    @Override
    public Author find(Integer id) {
        return context.selectFrom(com.example.orderservice.tables.Author.AUTHOR)
            .where(com.example.orderservice.tables.Author.AUTHOR.ID.eq(id))
            .fetchOptional()
            .orElseThrow(EntityNotFoundException::new)
            .into(Author.class);
    }

    @Override
    public List<Author> findAll() {
        return context.selectFrom(com.example.orderservice.tables.Author.AUTHOR)
            .fetch()
            .into(Author.class);
    }

    @Override
    public Boolean delete(Integer id) {
        return context.delete(com.example.orderservice.tables.Author.AUTHOR)
            .where(com.example.orderservice.tables.Author.AUTHOR.ID.eq(id))
            .execute() == SUCCESS_CODE;
    }
}
