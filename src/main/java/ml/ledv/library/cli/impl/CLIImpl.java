package ml.ledv.library.cli.impl;

import ml.ledv.library.cli.CLI;
import ml.ledv.library.cli.task.Task;
import ml.ledv.library.db.entity.impl.BookEntity;
import ml.ledv.library.db.entity.impl.UserEntity;
import ml.ledv.library.db.service.BookService;
import ml.ledv.library.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CLIImpl implements CLI {

    private TaskHandler taskHandler;

    private UserService userService;
    private BookService bookService;
    private List<Task> tasks;

    private Scanner scanner;

    @Autowired
    public CLIImpl(final TaskHandler taskHandler, final UserService userService, final BookService bookService) {
        this.taskHandler = taskHandler;
        this.userService = userService;
        this.bookService = bookService;
        scanner = new Scanner(System.in);
        tasks = new ArrayList<>();
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

    private void userServiceMenu() {

    }

    private void addUserMenu() {

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
}
