package ru.otus.jdbcprj;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.jdbcprj.dao.AuthorRepository;
import ru.otus.jdbcprj.model.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(AuthorRepository.class)
public class AuthorRepositoryImplTest {

    private static final int EXPECTED_NUMBER_OF_AUTHORS = 6;
    private static final long AUTHOR_ID = 5;

    @Autowired
    AuthorRepository repo;

    @DisplayName("Should return 6 records")
    @Test
    public void test_getAll() {
        List<Author> authors = repo.findAll();
        assertEquals(EXPECTED_NUMBER_OF_AUTHORS, authors.size());
    }

    @DisplayName("Find by id")
    @Test
    public void test_getById() {
        Author author = repo.findById(AUTHOR_ID);
        assertThat(author).isNotNull();
        assertEquals(AUTHOR_ID, author.getId());
    }
}
