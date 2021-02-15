package ru.otus.jdbcprj.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.jdbcprj.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBookId(long id);

}
