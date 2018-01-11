package ml.ledv.library.cli.task.impl;

import ml.ledv.library.cli.impl.TaskHandler;
import ml.ledv.library.cli.task.Task;
import ml.ledv.library.cli.utils.Printer;

import java.util.Scanner;

public class ShowLibraryMenu implements Task {

    private TaskHandler taskHandler;
    private Scanner scanner;

    public ShowLibraryMenu(final TaskHandler taskHandler) {
        this.taskHandler = taskHandler;
        scanner = new Scanner(System.in);
    }

    @Override
    public Task execute() {
        Printer.printLibraryMenu(taskHandler, scanner);
        return this;
    }

    @Override
    public void undo() {
        Printer.printLibraryMenu(taskHandler, scanner);
    }
}
