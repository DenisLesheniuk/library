package ml.ledv.library.cli.service.impl;

import ml.ledv.library.cli.service.BookLibraryService;

import ml.ledv.library.db.common.entity.CommonBookEntity;
import ml.ledv.library.db.common.entity.CommonUserEntity;
import ml.ledv.library.db.common.service.CommonBookService;
import ml.ledv.library.db.common.service.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommonBookLibraryService implements BookLibraryService {

    private CommonUserService userService;

    private CommonBookService bookService;

    @Autowired
    public CommonBookLibraryService(final CommonUserService userService, final CommonBookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public void createBook(final String name) {
        bookService.createBook(name);
    }

    @Override
    public void deleteBook(final String id) {

        final Optional<CommonBookEntity> optionalBookEntity = bookService.getBookById(id);

        if (!optionalBookEntity.isPresent()) {
            System.out.println("Book with id " + id + " is not exist!");
            return;
        } else {
            bookService.deleteBook(optionalBookEntity.get());
        }
    }


    @Override
    public void reserveBook(final String bookId, final String userId) {
        final Optional<CommonBookEntity> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + bookId + " is not exist! ");
            return;
        } else {

            final CommonBookEntity book = bookOptional.get();

            if (book.getUser() != null) {
                System.out.println("Book " + book.getName() + " is reserved by " + book.getUser().getId());
                return;
            } else {

                final Optional<CommonUserEntity> userOptional = userService.getUserById(userId);

                if (!userOptional.isPresent()) {
                    System.out.println("User with id " + userId + " is not exist! ");
                    return;
                } else {

                    final CommonUserEntity user = userOptional.get();

                    book.setUser(user);
                    user.getBooks().add(book);

                    bookService.updateBook(book);
                    userService.updateUser(user);
                }
            }
        }
    }

    @Override
    public void showBooks() {
        for (CommonBookEntity book : bookService.getAll()) {
            System.out.println();
            System.out.println("******************************************************");
            System.out.println("Id:        " + book.getId());
            System.out.println("Book name: " + book.getName());
            if (book.getUser() != null) {
                System.out.println("User:      " + "id    " + book.getUser().getId());
                System.out.println("           login " + book.getUser().getLogin());
            }
            System.out.println("******************************************************");
        }
    }

    @Override
    public void showFreeBook() {
        for (CommonBookEntity book : bookService.getAllFree()) {
            System.out.println();
            System.out.println("******************************************************");
            System.out.println("Id:        " + book.getId());
            System.out.println("Book name: " + book.getName());
            System.out.println("******************************************************");
        }
    }

    @Override
    public void createUser(final String login) {
        userService.createUser(login);
    }

    @Override
    public void deleteUser(final String id) {
        final Optional<CommonUserEntity> userOptional = userService.getUserById(id);

        if (!userOptional.isPresent()) {
            System.out.println("User with id " + id + " is not exist!");
            return;
        } else {
            userService.deleteUser(userOptional.get());
        }
    }

    @Override
    public void showUsers() {
        for (CommonUserEntity user : userService.getAll()) {
            System.out.println();
            System.out.println("******************************************************");
            System.out.println("Id:           " + user.getId());
            System.out.println("User's login: " + user.getLogin());
            System.out.println("Books:        ");
            for (CommonBookEntity book : user.getBooks()) {
                System.out.println();
                System.out.println("-----------------------*********-----------------------");
                System.out.println("Id:        " + book.getId());
                System.out.println("Book name: " + book.getName());
                System.out.println("-------------------------------------------------------");
            }
            System.out.println("******************************************************");
        }
    }

    @Override
    public void cancelReservation(final String id) {

        final Optional<CommonBookEntity> bookOptional = bookService.getBookById(id);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + id + " is not exist!");
            return;
        } else {
            bookService.removeUser(bookOptional.get());
        }
    }
}