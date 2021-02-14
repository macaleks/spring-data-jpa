package ru.otus.jdbcprj.dao;

import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.otus.jdbcprj.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom {

    List<Comment> findByBookId(long id);

}
