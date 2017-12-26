package ml.ledv.library.db.service;

import ml.ledv.library.db.nosql.entity.Book;
import ml.ledv.library.db.nosql.entity.User;

import java.util.List;

public interface BookLibraryService {

    void createBook(String name);

    void deleteBook(String id);

    void reserveBook(String bookId, String userId);

    List<Book> getBooks();

    List<Book> getFreeBook();

    void createUser(String login);

    void deleteUser(String id);

    List<User> getUsers();

    void returnBook(String id);
}
