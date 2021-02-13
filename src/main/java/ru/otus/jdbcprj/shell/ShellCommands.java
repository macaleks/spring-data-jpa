package ru.otus.jdbcprj.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.jdbcprj.model.Author;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.model.Comment;
import ru.otus.jdbcprj.model.Genre;
import ru.otus.jdbcprj.service.AuthorService;
import ru.otus.jdbcprj.service.BookService;
import ru.otus.jdbcprj.service.CommentService;
import ru.otus.jdbcprj.service.GenreService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ShellComponent
public class ShellCommands {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    private Map<Long, Genre> genres;
    private Map<Long, Author> authors;
    private Map<Long, Book> books;

    public ShellCommands(BookService bookService, AuthorService authorService, GenreService genreService, CommentService commentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
    }

    @ShellMethod(value = "Get genres", key = {"g", "genre"})
    public String getGenres() {
        return Stream.concat(getHeader("id, name"),
                genres.values().stream()
                        .map(Genre::toString))
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Get authors", key = {"a", "author"})
    public String getAuthors() {
        return Stream.concat(getHeader("id, first_name, second_name"),
                authors.values().stream()
                        .map(Author::toString))
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Get books", key = {"b", "book"})
    public String getBooks() {
        extractBooks();
        return Stream.concat(getHeader("id, name, author, genre"),
                books.values().stream()
                        .map(Book::toString))
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Delete a book", key = {"d", "delete"})
    public void deleteBook(String id) {
        bookService.deleteById(Long.valueOf(id));
    }

    @ShellMethod(value = "Create a book", key = {"c", "create"})
    public void createBook(String bookName, String authorId, String genreId) {
        Book book = new Book(0L, bookName, authors.get(Long.valueOf(authorId)), genres.get(Long.valueOf(genreId)));
        bookService.save(book);
    }

    @ShellMethod(value = "Update a book name", key = {"u", "update"})
    public void updateBook(String id, String bookName) {
        bookService.updateNameById(Long.valueOf(id), bookName);
    }

    @ShellMethod(value = "List a book comments", key = {"lc", "list"})
    public String listBookComments(String bookId) {
        List<Comment> comments = commentService.findByBookId(Long.valueOf(bookId));
        return comments.stream()
                .map(Comment::toString)
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "Add comment", key = {"ac", "add"})
    public String addComment(String bookId, String text) {
        Book book = new Book();
        book.setId(Long.valueOf(bookId));
        Comment comment = new Comment(0L, book, text);
        return commentService.save(comment).toString();
    }

    @PostConstruct
    private void init() {
        authors = authorService.getAll().stream()
                .collect(Collectors.toMap(k -> k.getId(), Function.identity()));
        genres = genreService.getAll().stream()
                .collect(Collectors.toMap(k -> k.getId(), Function.identity()));
        books = new HashMap<>();
    }

    private Stream<String> getHeader(String header) {
        return Stream.of(header);
    }

    private void extractBooks() {
        books = bookService.getAll().stream()
                .collect(Collectors.toMap(k -> k.getId(), Function.identity()));
    }
}
