package ml.ledv.library.db.service.impl;

import ml.ledv.library.db.service.BookLibraryService;
import ml.ledv.library.db.nosql.entity.Book;
import ml.ledv.library.db.nosql.entity.User;
import ml.ledv.library.db.nosql.service.BookService;
import ml.ledv.library.db.nosql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("mongodbService")
public class BLSMongoDB implements BookLibraryService {

    private UserService userService;

    private BookService bookService;

    @Autowired
    public BLSMongoDB(final UserService userService, final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public void createBook(final String name) {
        bookService.createBook(name);
    }

    @Override
    public void deleteBook(final String id) {

        final Optional<Book> bookOptional = bookService.getBookById(id);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + id + " is not exist!");
            return;
        } else {
            bookService.deleteBook(bookOptional.get());
        }
    }

    @Override
    public void reserveBook(final String bookId, final String userId) {

        final Optional<Book> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + bookId + " is not exist! ");
            return;
        } else {

            final Book book = bookOptional.get();

            if (book.getUser() != null) {
                System.out.println("Book " + book.getName() + " is reserved by " + book.getUser().getId());
                return;
            } else {

                final Optional<User> userOptional = userService.getUserById(userId);

                if (!userOptional.isPresent()) {
                    System.out.println("User with id " + userId + " is not exist! ");
                    return;
                } else {

                    final User user = userOptional.get();

                    book.setUser(user);
                    user.getBooks().add(book);

                    userService.updateUser(user);
                    bookService.updateBook(book);
                }
            }
        }
    }

    @Override
    public void showBooks() {
        for (Book book : bookService.getAll()) {
            System.out.println();
            System.out.println("************************");
            System.out.println("Id:        " + book.getId());
            System.out.println("Book name: " + book.getName());
            System.out.println("User:      " + book.getUser());
            System.out.println("************************");
        }
    }

    @Override
    public void showFreeBook() {
        for (Book book : bookService.getAllFree()) {
            System.out.println();
            System.out.println("************************");
            System.out.println("Id:        " + book.getId());
            System.out.println("Book name: " + book.getName());
            System.out.println("************************");
        }
    }

    @Override
    public void createUser(final String login) {
        userService.createUser(login);
    }

    @Override
    public void deleteUser(final String id) {

        final Optional<User> userOptional = userService.getUserById(id);

        if (!userOptional.isPresent()) {
            System.out.println("User with id " + id + " is not exist!");
            return;
        } else {
            userService.deleteUser(userOptional.get());
        }
    }

    @Override
    public void showUsers() {
        for (User user : userService.getAll()) {
            System.out.println();
            System.out.println("************************");
            System.out.println("Id:           " + user.getId());
            System.out.println("User's login: " + user.getLogin());
            System.out.println("Books:        ");
            for (Book book : user.getBooks()) {
                System.out.println();
                System.out.println("------------------------");
                System.out.println("Id:        " + book.getId());
                System.out.println("Book name: " + book.getName());
                System.out.println("------------------------");
            }
            System.out.println("************************");
        }
    }

    @Override
    public void cancelReservation(final String id) {

        final Optional<Book> bookOptional = bookService.getBookById(id);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + id + " is not exist!");
            return;
        } else {
            bookService.removeUser(bookOptional.get());
        }
    }
}
