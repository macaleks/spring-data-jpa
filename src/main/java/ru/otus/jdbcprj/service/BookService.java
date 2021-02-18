package ru.otus.jdbcprj.service;

import ru.otus.jdbcprj.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book save(Book book);

    void deleteById(long id);

    void updateNameById(Book book);

    Book getById(long id);
}
