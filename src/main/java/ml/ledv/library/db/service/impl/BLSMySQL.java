package ml.ledv.library.db.service.impl;

import ml.ledv.library.db.service.BookLibraryService;
import ml.ledv.library.db.nosql.entity.Book;
import ml.ledv.library.db.nosql.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mysqlService")
public class BLSMySQL implements BookLibraryService {

    @Override
    public void createBook(final String name) {

    }

    @Override
    public void deleteBook(final String id) {

    }

    @Override
    public void reserveBook(final String bookId, final String userId) {

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
    public void createUser(final String login) {

    }

    @Override
    public void deleteUser(final String id) {

    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public void returnBook(final String id) {

    }
}
