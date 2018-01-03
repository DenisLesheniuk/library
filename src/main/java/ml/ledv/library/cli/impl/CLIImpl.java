package ml.ledv.library.cli.impl;

import ml.ledv.library.cli.CLI;
import ml.ledv.library.cli.service.BookLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CLIImpl implements CLI {

    private BookLibraryService bookLibraryService;

    private Scanner scanner;

    @Autowired
    public CLIImpl(final BookLibraryService bookLibraryService) {
        this.bookLibraryService = bookLibraryService;
        scanner = new Scanner(System.in);
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

        bookLibraryService.createBook(bookName);

        System.out.println("Created book - " + bookName);
    }

    private void deleteBook() {

        String bookId = null;

        while (bookId == null) {
            System.out.println("Enter book name: ");

            bookId = scanner.nextLine();
        }

        bookLibraryService.deleteBook(bookId);

        System.out.println("Deleted book - " + bookId);
    }

    private void returnBook() {

        String bookId = null;

        while (bookId == null) {
            System.out.println("Enter book id: ");

            bookId = scanner.nextLine();
        }

        bookLibraryService.cancelReservation(bookId);

        System.out.println("BookDocument is returned - " + bookId);
    }

    private void showAll() {
        bookLibraryService.showBooks();
    }

    private void showAllFree() {
        bookLibraryService.showFreeBook();
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

        bookLibraryService.reserveBook(bookId, userId);

        System.out.println("BookDocument " + bookId + " is reserved " + " by " + userId);
    }

    private void userServiceMenu() {
        while (true) {

            System.out.println("UserDocument service menu.");

            System.out.println("1. Create User");
            System.out.println("2. Delete User");
            System.out.println("3. Show all.");
            System.out.println("4. Back.");

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

            userLogin = scanner.nextLine();
        }

        bookLibraryService.createUser(userLogin);

        System.out.println("Created user - " + userLogin);
    }

    private void deleteUser() {

        String userId = null;

        while (userId == null) {
            System.out.println("Enter user id: ");

            userId = scanner.nextLine();
        }

        bookLibraryService.deleteUser(userId);

        System.out.println("Deleted user - " + userId);
    }

    private void showUsers() {
        bookLibraryService.showUsers();
    }
}
