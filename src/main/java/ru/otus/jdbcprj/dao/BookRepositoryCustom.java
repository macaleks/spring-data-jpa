package ru.otus.jdbcprj.dao;

import ru.otus.jdbcprj.model.Book;

public interface BookRepositoryCustom {

    void updateNameById(long id, String name);

    Book create(Book book);
}
