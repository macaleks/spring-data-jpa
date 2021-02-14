package ru.otus.jdbcprj.dao;

import ru.otus.jdbcprj.model.Author;
import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class BookRepositoryImpl implements BookRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    public BookRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void updateNameById(long id, String name) {
        Query query = entityManager.createQuery("update Book b " +
                "set b.name = :name " +
                "where b.id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Book create(Book book) {
        if (book.getId() == 0L) {
            Author author = entityManager.find(Author.class, book.getAuthor().getId());
            Genre genre = entityManager.find(Genre.class, book.getGenre().getId());
            book.setAuthor(author);
            book.setGenre(genre);
            entityManager.persist(book);
            return book;
        }
        return entityManager.merge(book);
    }
}
