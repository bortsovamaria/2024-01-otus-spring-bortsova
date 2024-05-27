package ru.otus.spring.homework14.models.nosql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class BookDocument {

    @Id
    private String id;

    private String title;

    private AuthorDocument author;

    private GenreDocument genre;

}