package ml.ledv.library.db.nosql.service;

import ml.ledv.library.db.nosql.entity.impl.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void createBook(String name);

    void deleteBook(Book book);

    List<Book> getAll();

    List<Book> getAllFree();

    Optional<Book> getBookById(String id);

    void updateBook(Book book);

    void removeUser(Book book);
}
