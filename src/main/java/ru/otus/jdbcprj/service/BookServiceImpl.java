package ru.otus.jdbcprj.service;

import org.springframework.stereotype.Service;
import ru.otus.jdbcprj.dao.BookRepository;
import ru.otus.jdbcprj.model.Book;

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

    @Transactional
    @Override
    public Book save(Book book) {
        return repo.create(book);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        repo.deleteById(id);
    }

    @Transactional
    @Override
    public void updateNameById(long id, String name) {
        repo.updateNameById(id, name);
    }

    @Override
    public Book getById(long id) {
        return repo.findById(id);
    }


}
