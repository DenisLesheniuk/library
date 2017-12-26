package ml.ledv.library.cli.impl;

import ml.ledv.library.cli.CLI;
import ml.ledv.library.db.service.BookLibraryService;
import ml.ledv.library.db.nosql.entity.Book;
import ml.ledv.library.db.nosql.entity.User;
import ml.ledv.library.db.sql.entity.impl.BookEntity;

import java.util.Scanner;

public class CLIImpl implements CLI {

    private BookLibraryService bookLibraryService;

    public CLIImpl(final BookLibraryService bookLibraryService) {
        this.bookLibraryService = bookLibraryService;
    }

    @Override
    public void start() {
        System.out.println(bookLibraryService);

        while (true) {

            System.out.println("1. Add book.");
            System.out.println("2. Delete book.");
            System.out.println("3. Return book.");
            System.out.println("4. Show all.");
            System.out.println("5. Show all free.");
            System.out.println("6. Reserve book.");
            System.out.println("7. User service.");

            final Scanner scanner = new Scanner(System.in);
            final String choice = scanner.nextLine();

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
                    returnBook();
                    break;
                }
                case "4": {
                    showBooks();
                    break;
                }
                case "5": {
                    showFreeBooks();
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

            final Scanner scanner = new Scanner(System.in);
            bookName = scanner.nextLine();
        }

        bookLibraryService.createBook(bookName);

        System.out.println("Created book - " + bookName);
    }

    private void deleteBook() {

        String bookId = null;

        while (bookId == null) {
            System.out.println("Enter book name: ");

            final Scanner scanner = new Scanner(System.in);
            bookId = scanner.nextLine();
        }

        bookLibraryService.deleteBook(bookId);

        System.out.println("Deleted book - " + bookId);
    }

    private void returnBook() {

        String bookId = null;

        while (bookId == null) {
            System.out.println("Enter user id: ");

            final Scanner scanner = new Scanner(System.in);
            bookId = scanner.nextLine();
        }

        bookLibraryService.cancelReservation(bookId);

        System.out.println("Book is returned - " + bookId);
    }

    private void showBooks() {
        for (BookEntity book : ()bookLibraryService.getBooks()) {
            System.out.println(book);
        }
    }

    private void showFreeBooks() {
        for (BookEntity book : bookLibraryService.getFreeBook()) {
            System.out.println(book);
        }
    }

    private void reserveBook() {

        String bookId = null;
        String userId = null;

        while (bookId == null) {
            System.out.println("Enter book id: ");

            final Scanner scanner = new Scanner(System.in);
            bookId = scanner.nextLine();
        }
        while (userId == null) {
            System.out.println("Enter user id: ");

            final Scanner scanner = new Scanner(System.in);
            userId = scanner.nextLine();
        }

        bookLibraryService.reserveBook(bookId, userId);

        System.out.println("Book " + bookId + " is reserved " + " by " + userId);
    }

    private void userServiceMenu() {
        while (true) {

            System.out.println("User service menu.");

            System.out.println("1. Create User");
            System.out.println("2. Delete User");
            System.out.println("3. Show all.");
            System.out.println("4. Back.");

            final Scanner scanner = new Scanner(System.in);
            final String choice = scanner.nextLine();
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

            final Scanner scanner = new Scanner(System.in);
            userLogin = scanner.nextLine();
        }

        bookLibraryService.createUser(userLogin);

        System.out.println("Created user - " + userLogin);
    }

    private void deleteUser() {

        String userId = null;

        while (userId == null) {
            System.out.println("Enter user id: ");

            final Scanner scanner = new Scanner(System.in);
            userId = scanner.nextLine();
        }

        bookLibraryService.deleteUser(userId);

        System.out.println("Deleted user - " + userId);
    }

    private void showUsers() {
        for (User user : bookLibraryService.getUsers()) {
            System.out.println(user);
        }
    }

}
