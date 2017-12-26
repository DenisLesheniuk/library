package ml.ledv.library.db.sql.service;

import ml.ledv.library.db.sql.entity.impl.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void createBook(String name);

    void deleteBook(BookEntity book);

    List<BookEntity> getAll();

    List<BookEntity> getAllFree();

    Optional<BookEntity> getBookById(String id);

    void updateBook(BookEntity book);

    void removeUser(BookEntity book);
}
