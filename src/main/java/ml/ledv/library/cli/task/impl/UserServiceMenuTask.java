package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.impl.TaskHandler;
import ml.ledv.library.cli.task.Task;

import java.util.Scanner;

public class UserServiceMenuTask implements Task {

    private TaskHandler taskHandler;
    private Scanner scanner;

    public UserServiceMenuTask(final TaskHandler taskHandler) {
        this.taskHandler = taskHandler;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Task execute() {
        printUserServiceMenu();
        return this;
    }

    @Override
    public void undo() {
        printUserServiceMenu();
    }

    private void printUserServiceMenu() {

        String choice;

        while (true) {

            System.out.println("UserDocument service menu.");
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

                    break;
                }
                case "3": {

                    break;
                }
                case "4": {
                    return;
                }
                case "5": {
                    taskHandler.undo();
                    return;
                }
                default: {
                    System.out.println("Wrong choice!");
                }
            }
        }
    }
}
