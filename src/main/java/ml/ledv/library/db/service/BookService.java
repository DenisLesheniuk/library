package ml.ledv.library.db.service;

import ml.ledv.library.db.entity.impl.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookEntity createBook(String name);

    void deleteBook(BookEntity book);

    List<BookEntity> getAll();

    Optional<BookEntity> getBookById(String id);

    void updateBook(BookEntity book);
}
