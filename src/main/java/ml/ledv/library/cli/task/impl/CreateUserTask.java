package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.task.Task;
import ml.ledv.library.db.entity.impl.UserEntity;
import ml.ledv.library.db.service.UserService;

import java.util.Scanner;

public class CreateUserTask implements Task {

    private UserService userService;
    private Scanner scanner;

    private UserEntity userEntity;

    public CreateUserTask(final UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Task execute() {

        String userLogin = null;

        while (userLogin == null) {
            System.out.println("\nEnter user login: ");
            userLogin = scanner.nextLine();
        }

        userEntity = userService.createUser(userLogin);

        System.out.println("Created user with name - " + userLogin + "\n");
        return this;
    }

    @Override
    public void undo() {
        System.out.println("\nUndo creating user - " + userEntity.getLogin());

        userService.deleteUser(userEntity);

        System.out.println(".... user " + userEntity.getLogin() + " is deleted.");
    }
}
