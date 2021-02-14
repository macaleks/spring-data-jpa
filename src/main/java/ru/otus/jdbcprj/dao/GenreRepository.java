package ru.otus.jdbcprj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.jdbcprj.model.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findById(long id);
}
