package ml.ledv.library.db;

import ml.ledv.library.db.nosql.entity.impl.Book;
import ml.ledv.library.db.nosql.entity.impl.User;

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
