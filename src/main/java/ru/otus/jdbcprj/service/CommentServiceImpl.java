package ru.otus.jdbcprj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.jdbcprj.dao.CommentRepository;
import ru.otus.jdbcprj.model.Comment;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repo;

    @Autowired
    public CommentServiceImpl(CommentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Comment> findByBookId(long id) {
        return repo.findByBookId(id);
    }

    @Override
    public Comment save(Comment comment) {
        return repo.save(comment);
    }
}
