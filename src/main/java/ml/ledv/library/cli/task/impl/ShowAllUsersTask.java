package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.task.Task;
import ml.ledv.library.cli.utils.Printer;
import ml.ledv.library.db.entity.impl.UserEntity;
import ml.ledv.library.db.service.UserService;

import java.util.List;
import java.util.Scanner;

public class ShowAllUsersTask implements Task {

    private UserService userService;
    private Scanner scanner;

    private List<UserEntity> users;

    public ShowAllUsersTask(final UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Task execute() {
        System.out.println("\n                     ALL USERS\n");

        users = userService.getAll();
        Printer.printUsers(users);
        return this;
    }

    @Override
    public void undo() {

        System.out.println("\n                     ALL USERS\n");
        Printer.printUsers(users);
    }

}
