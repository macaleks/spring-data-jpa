package ru.otus.jdbcprj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.jdbcprj.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
