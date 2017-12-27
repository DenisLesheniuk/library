package ml.ledv.library.db.nosql.service;

import ml.ledv.library.db.nosql.document.BookDocument;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void createBook(String name);

    void deleteBook(BookDocument bookDocument);

    List<BookDocument> getAll();

    List<BookDocument> getAllFree();

    Optional<BookDocument> getBookById(String id);

    void updateBook(BookDocument bookDocument);

    void removeUser(BookDocument bookDocument);
}
