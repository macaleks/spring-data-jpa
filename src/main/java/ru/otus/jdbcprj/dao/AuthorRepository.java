package ru.otus.jdbcprj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.jdbcprj.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
