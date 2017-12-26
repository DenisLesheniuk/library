package ml.ledv.library.db.sql.service;

import ml.ledv.library.db.BookLibraryService;
import ml.ledv.library.db.nosql.entity.impl.Book;
import ml.ledv.library.db.nosql.entity.impl.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mysqlService")
public class BLSMySQL implements BookLibraryService {

    @Override
    public void createBook(String name) {

    }

    @Override
    public void deleteBook(String id) {

    }

    @Override
    public void reserveBook(String bookId, String userId) {

    }

    @Override
    public List<Book> getBooks() {
        return null;
    }

    @Override
    public List<Book> getFreeBook() {
        return null;
    }

    @Override
    public void createUser(String login) {

    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void returnBook(String id) {

    }
}
