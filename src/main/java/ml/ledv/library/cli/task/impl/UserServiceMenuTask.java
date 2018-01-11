package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.impl.TaskHandler;
import ml.ledv.library.cli.task.Task;
import ml.ledv.library.cli.utils.Printer;

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
        Printer.printUserServiceMenu(taskHandler, scanner);
        return this;
    }

    @Override
    public void undo() {
        Printer.printUserServiceMenu(taskHandler, scanner);
    }
}
