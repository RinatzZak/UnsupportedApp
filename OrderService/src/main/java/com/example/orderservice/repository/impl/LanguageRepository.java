package com.example.orderservice.repository.impl;

import com.example.orderservice.entity.Language;
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
public class LanguageRepository implements CrudRepository<Language> {

    private final DSLContext context;
    @SneakyThrows
    @Override
    public Language insert(Language language) {
        return context.insertInto(com.example.orderservice.tables.Language.LANGUAGE)
            .set(context.newRecord(com.example.orderservice.tables.Language.LANGUAGE, language))
            .returning()
            .fetchOptional()
            .orElseThrow(DataFormatException::new)
            .into(Language.class);
    }

    @SneakyThrows
    @Override
    public Language update(Language language) {
        return context.update(com.example.orderservice.tables.Language.LANGUAGE)
            .set(context.newRecord(com.example.orderservice.tables.Language.LANGUAGE, language))
            .where(com.example.orderservice.tables.Language.LANGUAGE.ID.eq(language.getId()))
            .returning()
            .fetchOptional()
            .orElseThrow(DataFormatException::new)
            .into(Language.class);
    }

    @Override
    public Language find(Integer id) {
        return context.selectFrom(com.example.orderservice.tables.Language.LANGUAGE)
            .where(com.example.orderservice.tables.Language.LANGUAGE.ID.eq(id))
            .fetchOptional()
            .orElseThrow(EntityNotFoundException::new)
            .into(Language.class);
    }

    @Override
    public List<Language> findAll() {
        return context.selectFrom(com.example.orderservice.tables.Language.LANGUAGE)
            .fetch()
            .into(Language.class);
    }

    @Override
    public Boolean delete(Integer id) {
        return context.delete(com.example.orderservice.tables.Language.LANGUAGE)
            .where(com.example.orderservice.tables.Language.LANGUAGE.ID.eq(id))
            .execute() == SUCCESS_CODE;
    }
}
