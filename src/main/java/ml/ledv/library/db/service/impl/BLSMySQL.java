package ml.ledv.library.db.service.impl;

import ml.ledv.library.db.service.BookLibraryService;

import ml.ledv.library.db.sql.entity.impl.BookEntity;
import ml.ledv.library.db.sql.entity.impl.UserEntity;
import ml.ledv.library.db.sql.service.BookService;
import ml.ledv.library.db.sql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            System.out.println("BookDocument with id " + id + " is not exist!");
            return;
        } else {
            bookService.deleteBook(optionalBookEntity.get());
        }
    }


    @Override
    public void reserveBook(final String bookId, final String userId) {
        final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            System.out.println("BookDocument with id " + bookId + " is not exist! ");
            return;
        } else {

            final BookEntity book = bookOptional.get();

            if (book.getUserEntity() != null) {
                System.out.println("BookDocument " + book.getName() + " is reserved by " + book.getUserEntity().getId());
                return;
            } else {

                final Optional<UserEntity> userOptional = userService.getUserById(userId);

                if (!userOptional.isPresent()) {
                    System.out.println("UserDocument with id " + userId + " is not exist! ");
                    return;
                } else {

                    final UserEntity user = userOptional.get();

                    book.setUserEntity(user);
                    user.getBooks().add(book);

                    bookService.updateBook(book);
                    userService.updateUser(user);
                }
            }
        }
    }

    @Override
    public void showBooks() {
        for (BookEntity book : bookService.getAll()) {
            System.out.println();
            System.out.println("************************");
            System.out.println("Id:        " + book.getId());
            System.out.println("BookDocument name: " + book.getName());
            if (book.getUserEntity() != null) {
                System.out.println("UserDocument:      " + "id    " + book.getUserEntity().getId());
                System.out.println("           login " + book.getUserEntity().getLogin());
            }
            System.out.println("************************");
        }
    }

    @Override
    public void showFreeBook() {
        for (BookEntity book : bookService.getAllFree()) {
            System.out.println();
            System.out.println("************************");
            System.out.println("Id:        " + book.getId());
            System.out.println("BookDocument name: " + book.getName());
            System.out.println("************************");
        }
    }

    @Override
    public void createUser(final String login) {
        userService.createUser(login);
    }

    @Override
    public void deleteUser(final String id) {
        final Optional<UserEntity> userOptional = userService.getUserById(id);

        if (!userOptional.isPresent()) {
            System.out.println("UserDocument with id " + id + " is not exist!");
            return;
        } else {
            userService.deleteUser(userOptional.get());
        }
    }

    @Override
    public void showUsers() {
        for (UserEntity user : userService.getAll()) {
            System.out.println();
            System.out.println("************************");
            System.out.println("Id:           " + user.getId());
            System.out.println("UserDocument's login: " + user.getLogin());
            System.out.println("Books:        ");
            for (BookEntity book : user.getBooks()) {
                System.out.println();
                System.out.println("------------------------");
                System.out.println("Id:        " + book.getId());
                System.out.println("BookDocument name: " + book.getName());
                System.out.println("------------------------");
            }
            System.out.println("************************");
        }
    }

    @Override
    public void cancelReservation(final String id) {

        final Optional<BookEntity> bookOptional = bookService.getBookById(id);

        if (!bookOptional.isPresent()) {
            System.out.println("BookDocument with id " + id + " is not exist!");
            return;
        } else {
            bookService.removeUser(bookOptional.get());
        }
    }
}
