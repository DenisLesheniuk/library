package ml.ledv.library.cli.utils;

import ml.ledv.library.cli.impl.TaskHandler;
import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.entity.impl.UserEntity;

import java.util.List;
import java.util.Scanner;

public class Printer {

    public static void printBooks(final BookEntity book) {
        System.out.println();
        System.out.println("******************************************************");
        System.out.println("Id:        " + book.getId());
        System.out.println("Book name: " + book.getName());
        System.out.println("******************************************************");
    }

    public static void printUsers(final List<UserEntity> users) {
        for (UserEntity user : users) {
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

    public static void printUserServiceMenu(final TaskHandler taskHandler, final Scanner scanner) {

        String choice;

        while (true) {

            System.out.println("\n      USER SERVICE MENU\n");
            System.out.println("1. Create User");
            System.out.println("2. Delete User");
            System.out.println("3. Show all.");
            System.out.println("4. Back.");
            System.out.println("5. Undo.");
            choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    taskHandler.createUser();
                    break;
                }
                case "2": {
                    taskHandler.deleteUser();
                    break;
                }
                case "3": {
                    taskHandler.showAllUsers();
                    break;
                }
                case "4": {
                    return;
                }
                case "5": {
                    taskHandler.undo();
                    break;
                }
                default: {
                    System.out.println("Wrong choice!");
                }
            }
        }
    }

    public static void printLibraryMenu(final TaskHandler taskHandler, final Scanner scanner) {

        String choice;
        while (true) {
            System.out.println("\n      BOOK LIBRARY    \n");

            System.out.println("1. Add book.");
            System.out.println("2. Delete book.");
            System.out.println("3. Return book.");
            System.out.println("4. Show all.");
            System.out.println("5. Show all free.");
            System.out.println("6. Reserve book.");
            System.out.println("7. User service.");
            System.out.println("8. Undo.");

            choice = scanner.nextLine();

            switch (choice) {
                case "1": {
                    taskHandler.createBook();
                    break;
                }
                case "2": {
                    taskHandler.deleteBook();
                    break;
                }
                case "3": {
                    taskHandler.cancelBookReservation();
                    break;
                }
                case "4": {
                    taskHandler.showAllBooks();
                    break;
                }
                case "5": {
                    taskHandler.showAllFreBooks();
                    break;
                }
                case "6": {
                    taskHandler.reserveBook();
                    break;
                }
                case "7": {
                    taskHandler.showUserMenu();
                    break;
                }
                case "8": {
                    taskHandler.undo();
                    break;
                }
                default: {
                    System.out.println("Wrong choice!");
                }
            }
        }
    }
}
