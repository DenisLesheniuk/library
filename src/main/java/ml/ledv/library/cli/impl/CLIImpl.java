package ml.ledv.library.cli.impl;

import ml.ledv.library.cli.CLI;
import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.entity.impl.UserEntity;
import ml.ledv.library.db.service.BookService;
import ml.ledv.library.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CLIImpl implements CLI {

    private UserService userService;
    private BookService bookService;

    private Scanner scanner;

    @Autowired
    public CLIImpl(final UserService userService, final BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
        scanner = new Scanner(System.in);
    }

    @Override
    public void start() {

        String choice;

        while (true) {

            System.out.println("1. Add book.");
            System.out.println("2. Delete book.");
            System.out.println("3. Return book.");
            System.out.println("4. Show all.");
            System.out.println("5. Show all free.");
            System.out.println("6. Reserve book.");
            System.out.println("7. User service.");

            choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    addBook();
                    break;
                }
                case "2": {
                    deleteBook();
                    break;
                }
                case "3": {
                    cancelBookReservation();
                    break;
                }
                case "4": {
                    showAll();
                    break;
                }
                case "5": {
                    showAllFree();
                    break;
                }
                case "6": {
                    reserveBook();
                    break;
                }
                case "7": {
                    userServiceMenu();
                    break;
                }
                default: {
                    System.out.println("Wrong choice!");
                }
            }
        }
    }

    private void addBook() {

        String bookName = null;

        while (bookName == null) {
            System.out.println("Enter book name: ");
            bookName = scanner.nextLine();
        }

        bookService.createBook(bookName);

        System.out.println("Created book - " + bookName);
    }

    private void deleteBook() {

        String bookId = null;

        while (bookId == null) {
            System.out.println("Enter book name: ");
            bookId = scanner.nextLine();
        }

        final Optional<BookEntity> optionalBookEntity = bookService.getBookById(bookId);

        if (!optionalBookEntity.isPresent()) {
            System.out.println("Book with id " + bookId + " is not exist!");
            return;
        } else {
            bookService.deleteBook(optionalBookEntity.get());
        }

        System.out.println("Deleted book - " + bookId);
    }

    private void cancelBookReservation() {

        String bookId = null;
        String userId = null;

        while (bookId == null) {
            System.out.println("Enter book id: ");
            bookId = scanner.nextLine();
        }

        final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + bookId + " is not exist!");
            return;
        } else {

            while (userId == null) {
                System.out.println("Enter user id: ");
                userId = scanner.nextLine();
            }

            final Optional<UserEntity> userOptional = userService.getUserById(userId);
            if (!userOptional.isPresent()) {
                System.out.println("User with id " + userId + " is not exist!");
                return;
            } else {
                userService.removeBook(userOptional.get(), bookOptional.get());
            }
        }
        System.out.println("BookDocument is returned - " + bookId);
    }

    private void showAll() {

        for (BookEntity book : bookService.getAll()) {
            printBooks(book);
        }
    }

    private void showAllFree() {

        final List<BookEntity> bookEntities = bookService.getAll();
        Optional<UserEntity> userOptional = null;

        for (BookEntity book : bookEntities) {
            userOptional = userService.getUserByBook(book);
            if (!userOptional.isPresent()) {
                printBooks(book);
            }
        }
    }

    private void reserveBook() {

        String bookId = null;
        String userId = null;

        while (bookId == null) {

            System.out.println("Enter book id: ");
            bookId = scanner.nextLine();
        }
        while (userId == null) {

            System.out.println("Enter user id: ");
            userId = scanner.nextLine();
        }

        final Optional<BookEntity> bookOptional = bookService.getBookById(bookId);

        if (!bookOptional.isPresent()) {
            System.out.println("Book with id " + bookId + " is not exist! ");
            return;
        } else {

            final BookEntity book = bookOptional.get();

            if (userService.getUserByBook(book).isPresent()) {
                System.out.println("Book " + book.getName() + " is already reserved.");
                return;
            } else {

                final Optional<UserEntity> userOptional = userService.getUserById(userId);

                if (!userOptional.isPresent()) {
                    System.out.println("User with id " + userId + " is not exist! ");
                    return;
                } else {

                    final UserEntity user = userOptional.get();

                    user.getBooks().add(book);
                    userService.updateUser(user);
                }
            }
        }

        System.out.println("BookDocument " + bookId + " is reserved " + " by " + userId);
    }

    private void userServiceMenu() {

        String choice;

        while (true) {

            System.out.println("UserDocument service menu.");
            System.out.println("1. Create User");
            System.out.println("2. Delete User");
            System.out.println("3. Show all.");
            System.out.println("4. Back.");

            choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    addUserMenu();
                    break;
                }
                case "2": {
                    deleteUser();
                    break;
                }
                case "3": {
                    showUsers();
                    break;
                }
                case "4": {
                    return;
                }
                default: {
                    System.out.println("Wrong choice!");
                }
            }
        }
    }

    private void addUserMenu() {

        String userLogin = null;

        while (userLogin == null) {
            System.out.println("Enter user login: ");
            userLogin = scanner.nextLine();
        }

        userService.createUser(userLogin);

        System.out.println("Created user - " + userLogin);
    }

    private void deleteUser() {

        String userId = null;

        while (userId == null) {

            System.out.println("Enter user id: ");
            userId = scanner.nextLine();
        }

        final Optional<UserEntity> userOptional = userService.getUserById(userId);

        if (!userOptional.isPresent()) {
            System.out.println("User with id " + userId + " is not exist!");
            return;
        } else {
            userService.deleteUser(userOptional.get());
        }

        System.out.println("Deleted user - " + userId);
    }

    private void showUsers() {

        for (UserEntity user : userService.getAll()) {
            System.out.println();
            System.out.println("******************************************************");
            System.out.println("Id:           " + user.getId());
            System.out.println("User's login: " + user.getLogin());
            System.out.println("Books:        ");
            for (BookEntity book : user.getBooks()) {
                System.out.println();
                System.out.println("-----------------------*********-----------------------");
                System.out.println("Id:        " + book.getId());
                System.out.println("Book name: " + book.getName());
                System.out.println("-------------------------------------------------------");
            }
            System.out.println("******************************************************");
        }
    }

    private void printBooks(final BookEntity book) {
        System.out.println();
        System.out.println("******************************************************");
        System.out.println("Id:        " + book.getId());
        System.out.println("Book name: " + book.getName());
        System.out.println("******************************************************");
    }
}
