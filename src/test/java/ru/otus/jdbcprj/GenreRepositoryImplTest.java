package ru.otus.jdbcprj;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.jdbcprj.dao.GenreRepository;
import ru.otus.jdbcprj.model.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(GenreRepository.class)
public class GenreRepositoryImplTest {

    private static final int EXPECTED_NUMBER_OF_GENRES = 9;
    private static final long GENRE_ID = 7;

    @Autowired
    GenreRepository repo;

    @DisplayName("Should return 9 records")
    @Test
    public void test_getAll() {
        List<Genre> genre = repo.findAll();
        assertEquals(EXPECTED_NUMBER_OF_GENRES, genre.size());
    }

    @DisplayName("Find by id")
    @Test
    public void test_getById() {
        Genre genre = repo.findById(GENRE_ID);
        assertThat(genre).isNotNull();
        assertEquals(GENRE_ID, genre.getId());
    }
}
