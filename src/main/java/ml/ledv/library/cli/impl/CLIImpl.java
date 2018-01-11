package ml.ledv.library.cli.impl;

import ml.ledv.library.cli.CLI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CLIImpl implements CLI {

    private TaskHandler taskHandler;

    private Scanner scanner;

    @Autowired
    public CLIImpl(final TaskHandler taskHandler) {
        this.taskHandler = taskHandler;
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
