package ml.ledv.library.db.service.impl;

import ml.ledv.library.db.service.BookLibraryService;

import ml.ledv.library.db.sql.entity.impl.BookEntity;
import ml.ledv.library.db.sql.entity.impl.UserEntity;
import ml.ledv.library.db.sql.service.BookService;
import ml.ledv.library.db.sql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mysqlService")
public class BLSMySQL implements BookLibraryService {

    private UserService userService;

    private BookService bookService;

    @Autowired
    public BLSMySQL(final UserService userService, final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public void createBook(final String name) {
        bookService.createBook(name);
    }

    @Override
    public void deleteBook(final String id) {

        final Optional<BookEntity> optionalBookEntity = bookService.getBookById(id);

        if (!optionalBookEntity.isPresent()) {
            System.out.println("Book with id " + id + " is not exist!");
            return;
        } else {
            bookService.deleteBook(optionalBookEntity.get());
        }
    }


    @Override
    public void reserveBook(final String bookId, final String userId) {
        final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + bookId + " is not exist! ");
            return;
        } else {

            final BookEntity book = bookOptional.get();

            if (book.getUserEntity() != null) {
                System.out.println("Book " + book.getName() + " is reserved by " + book.getUserEntity().getId());
                return;
            } else {

                final Optional<UserEntity> userOptional = userService.getUserById(userId);

                if (!userOptional.isPresent()) {
                    System.out.println("User with id " + userId + " is not exist! ");
                    return;
                } else {

                    final UserEntity user = userOptional.get();

                    book.setUserEntity(user);
                    user.getBooks().add(book);

                    userService.updateUser(user);
                    bookService.updateBook(book);
                }
            }
        }
    }

    @Override
    public List<BookEntity> getBooks() {
        return bookService.getAll();
    }

    @Override
    public List<BookEntity> getFreeBook() {
        return bookService.getAllFree();
    }

    @Override
    public void createUser(final String login) {
        userService.createUser(login);
    }

    @Override
    public void deleteUser(final String id) {
        final Optional<UserEntity> userOptional = userService.getUserById(id);

        if (!userOptional.isPresent()) {
            System.out.println("User with id " + id + " is not exist!");
            return;
        } else {
            userService.deleteUser(userOptional.get());
        }
    }

    @Override
    public List<UserEntity> getUsers() {
        return userService.getAll();
    }

    @Override
    public void cancelReservation(final String id) {

        final Optional<BookEntity> bookOptional = bookService.getBookById(id);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + id + " is not exist!");
            return;
        } else {
            bookService.removeUser(bookOptional.get());
        }
    }
}
