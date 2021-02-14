package ru.otus.jdbcprj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.jdbcprj.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

    void deleteById(long id);

    Book findById(long id);
}
