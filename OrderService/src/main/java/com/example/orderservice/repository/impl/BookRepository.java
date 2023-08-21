package com.example.orderservice.repository.impl;

import com.example.orderservice.entity.Author;
import com.example.orderservice.entity.Book;
import com.example.orderservice.entity.Language;
import com.example.orderservice.repository.CrudRepository;
import com.example.orderservice.util.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.zip.DataFormatException;

@Repository
@RequiredArgsConstructor
public class BookRepository implements CrudRepository<Book> {
    private final DSLContext context;
    private final AuthorRepository authorRepository;
    private final LanguageRepository languageRepository;

    @SneakyThrows
    @Override
    public Book insert(Book book) {
        return context.insertInto(com.example.orderservice.tables.Book.BOOK)
            .set(context.newRecord(com.example.orderservice.tables.Book.BOOK, book))
            .returning()
            .fetchOptional()
            .orElseThrow(DataFormatException::new)
            .into(Book.class);
    }

    @SneakyThrows
    @Override
    public Book update(Book book) {
        return context.update(com.example.orderservice.tables.Book.BOOK)
            .set(context.newRecord(com.example.orderservice.tables.Book.BOOK, book))
            .where(com.example.orderservice.tables.Book.BOOK.ID.eq(book.getId()))
            .returning()
            .fetchOptional()
            .orElseThrow(DataFormatException::new)
            .into(Book.class);
    }

    @Override
    public Book find(Integer id) {
        Record record = context.select()
            .from(com.example.orderservice.tables.Book.BOOK)
            .join(com.example.orderservice.tables.Author.AUTHOR)
            .on(com.example.orderservice.tables.Book.BOOK.AUTHOR_ID.eq(com.example.orderservice.tables.Author.AUTHOR.ID))
            .join(com.example.orderservice.tables.Language.LANGUAGE)
            .on(com.example.orderservice.tables.Book.BOOK.LANGUAGE_ID.eq(com.example.orderservice.tables.Language.LANGUAGE.ID))
            .where(com.example.orderservice.tables.Book.BOOK.ID.eq(id))
            .fetchOne();

        if (record == null) {
            throw new EntityNotFoundException();
        }

        Book book = record.into(Book.class);
        book.setAuthor(record.into(Author.class));
        book.setLanguage(record.into(Language.class));

        return book;
    }

    @Override
    public List<Book> findAll() {
        return context.selectFrom(com.example.orderservice.tables.Book.BOOK)
            .fetch()
            .into(Book.class);
    }

    @Override
    public Boolean delete(Integer id) {
        return context.delete(com.example.orderservice.tables.Book.BOOK)
            .where(com.example.orderservice.tables.Book.BOOK.ID.eq(id))
            .execute() == SUCCESS_CODE;
    }
}
