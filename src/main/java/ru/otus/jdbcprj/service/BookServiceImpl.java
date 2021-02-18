package ru.otus.jdbcprj.service;

import org.springframework.stereotype.Service;
import ru.otus.jdbcprj.dao.BookRepository;
import ru.otus.jdbcprj.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    BookRepository repo;

    public BookServiceImpl(BookRepository bookDao) {
        this.repo = bookDao;
    }

    @Override
    public List<Book> getAll() {
        return repo.findAll();
    }

    @Override
    public Book save(Book book) {
        return repo.save(book);
    }

    @Override
    public void deleteById(long id) {
        repo.deleteById(id);
    }

    @Override
    public void updateNameById(Book book) {
        repo.save(book);
    }

    @Override
    public Book getById(long id) {
        return repo.findById(id).get();
    }


}
