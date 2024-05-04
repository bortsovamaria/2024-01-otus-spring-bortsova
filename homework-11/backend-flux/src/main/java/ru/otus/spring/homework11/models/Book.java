package ru.otus.spring.homework11.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table("books")
public class Book {

    @Id
    private Long id;

    private String title;

    @Column("author_id")
    private Long authorId;

    @Column("genre_id")
    private Long genreId;

    public Book(String title, Long authorId, Long genreId) {
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
    }
}