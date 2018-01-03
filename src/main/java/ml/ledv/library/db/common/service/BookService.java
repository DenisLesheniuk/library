package ml.ledv.library.db.common.service;

import ml.ledv.library.db.common.entity.BookEntity;

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
