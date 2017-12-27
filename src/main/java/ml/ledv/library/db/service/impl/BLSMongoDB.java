package ml.ledv.library.db.service.impl;

import ml.ledv.library.db.nosql.document.BookDocument;
import ml.ledv.library.db.nosql.document.UserDocument;
import ml.ledv.library.db.service.BookLibraryService;
import ml.ledv.library.db.nosql.service.BookService;
import ml.ledv.library.db.nosql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        final Optional<BookDocument> bookOptional = bookService.getBookById(id);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + id + " is not exist!");
            return;
        } else {
            bookService.deleteBook(bookOptional.get());
        }
    }

    @Override
    public void reserveBook(final String bookId, final String userId) {

        final Optional<BookDocument> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + bookId + " is not exist! ");
            return;
        } else {

            final BookDocument bookDocument = bookOptional.get();

            if (bookDocument.getUserDocument() != null) {
                System.out.println("Book " + bookDocument.getName() + " is reserved by " + bookDocument.getUserDocument().getId());
                return;
            } else {

                final Optional<UserDocument> userOptional = userService.getUserById(userId);

                if (!userOptional.isPresent()) {
                    System.out.println("User with id " + userId + " is not exist! ");
                    return;
                } else {

                    final UserDocument userDocument = userOptional.get();

                    bookDocument.setUserDocument(userDocument);
                    userDocument.getBookDocuments().add(bookDocument);

                    userService.updateUser(userDocument);
                    bookService.updateBook(bookDocument);
                }
            }
        }
    }

    @Override
    public void showBooks() {
        for (BookDocument bookDocument : bookService.getAll()) {
            System.out.println();
            System.out.println("************************");
            System.out.println("Id:        " + bookDocument.getId());
            System.out.println("Book name: " + bookDocument.getName());
            System.out.println("User:      " + bookDocument.getUserDocument());
            System.out.println("************************");
        }
    }

    @Override
    public void showFreeBook() {
        for (BookDocument bookDocument : bookService.getAllFree()) {
            System.out.println();
            System.out.println("************************");
            System.out.println("Id:        " + bookDocument.getId());
            System.out.println("Book name: " + bookDocument.getName());
            System.out.println("************************");
        }
    }

    @Override
    public void createUser(final String login) {
        userService.createUser(login);
    }

    @Override
    public void deleteUser(final String id) {

        final Optional<UserDocument> userOptional = userService.getUserById(id);

        if (!userOptional.isPresent()) {
            System.out.println("User with id " + id + " is not exist!");
            return;
        } else {
            userService.deleteUser(userOptional.get());
        }
    }

    @Override
    public void showUsers() {
        for (UserDocument userDocument : userService.getAll()) {
            System.out.println();
            System.out.println("************************");
            System.out.println("Id:           " + userDocument.getId());
            System.out.println("User's login: " + userDocument.getLogin());
            System.out.println("Books:        ");
            for (BookDocument bookDocument : userDocument.getBookDocuments()) {
                System.out.println();
                System.out.println("------------------------");
                System.out.println("Id:        " + bookDocument.getId());
                System.out.println("Book name: " + bookDocument.getName());
                System.out.println("------------------------");
            }
            System.out.println("************************");
        }
    }

    @Override
    public void cancelReservation(final String id) {

        final Optional<BookDocument> bookOptional = bookService.getBookById(id);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + id + " is not exist!");
            return;
        } else {
            bookService.removeUser(bookOptional.get());
        }
    }
}
