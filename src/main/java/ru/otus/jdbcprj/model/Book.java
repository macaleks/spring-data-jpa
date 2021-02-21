package ru.otus.jdbcprj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@NamedEntityGraph(name = "author-entity-graph", attributeNodes = {@NamedAttributeNode("author")})
public class Book {

    public Book(long id, String name, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToOne(targetEntity = Author.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_author")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5)
    private Author author;

    @OneToOne(targetEntity = Genre.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_genre")
    private Genre genre;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_book")
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 5)
    private List<Comment> comments;

    @Override
    public String toString() {
        return id + ", " + name + ", {" + author + "}, {" + genre + "}";
    }
}
