package ru.otus.jdbcprj.dao;

import ru.otus.jdbcprj.model.Book;
import ru.otus.jdbcprj.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CommentRepositoryImpl implements CommentRepositoryCustom {

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Comment addComment(Comment comment) {
        Book book = entityManager.find(Book.class, comment.getBook().getId());
        comment.setBook(book);
        entityManager.persist(comment);
        return comment;
    }
}
