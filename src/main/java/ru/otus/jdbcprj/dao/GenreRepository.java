package ru.otus.jdbcprj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.jdbcprj.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
