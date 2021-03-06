package ru.otus.jdbcprj;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.jdbcprj.dao.AuthorRepository;
import ru.otus.jdbcprj.dao.BookRepository;
import ru.otus.jdbcprj.dao.CommentRepository;
import ru.otus.jdbcprj.dao.GenreRepository;
import ru.otus.jdbcprj.model.Author;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.model.Comment;
import ru.otus.jdbcprj.model.Genre;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Transactional
public class BookRepositoryJapImplTest {

    private static final int EXPECTED_NUMBER_OF_BOOKS = 3;
    private static final long GENRE_ID = 3L;
    private static final long AUTHOR_ID = 5L;
    private static final long BOOK_ID = 3L;
    private static final String BOOK_NAME = "New book";

    @Autowired
    BookRepository bookRepo;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    TestEntityManager em;

    @DisplayName("Should return 3 records")
    @Test
    public void test_getAll() {
        List<Book> books = bookRepo.findAll();
        assertEquals(EXPECTED_NUMBER_OF_BOOKS, books.size());
    }

    @DisplayName("Check that a new record saved")
    @Test
    public void test_insert() {
        Author author = authorRepository.findById(AUTHOR_ID).get();
        Genre genre = genreRepository.findById(GENRE_ID).get();
        val book = new Book(0L, "Book name", author, genre);
        bookRepo.save(book);
        assertThat(book.getId()).isGreaterThan(0);
    }

    @DisplayName("Update name by id")
    @Test
    public void test_update() {
        val book = em.find(Book.class, BOOK_ID);
        String oldName = book.getName();
        em.detach(book);

        val bookToModify = bookRepo.findById(BOOK_ID).get();
        bookToModify.setName(BOOK_NAME);
        bookRepo.save(bookToModify);
        val updatedBook = em.find(Book.class, BOOK_ID);

        assertThat(updatedBook.getName()).isNotEqualTo(oldName).isEqualTo(BOOK_NAME);
    }

    @DisplayName("Delete by id")
    @Test
    public void test_delete() {
        val book = em.find(Book.class, BOOK_ID);
        assertThat(book).isNotNull();
        em.detach(book);

        bookRepo.deleteById(BOOK_ID);
        val deletedBook = em.find(Book.class, BOOK_ID);
        assertThat(deletedBook).isNull();
    }

    @DisplayName("Retrieve comments by book")
    @Test
    public void test_getComments() {
        val book = bookRepo.findById(BOOK_ID).get();
        List<Comment> comments = book.getComments();
        assertThat(comments.size()).isGreaterThan(0);
    }
}
