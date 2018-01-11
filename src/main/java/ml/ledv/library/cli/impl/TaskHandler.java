package ml.ledv.library.cli.impl;

import ml.ledv.library.cli.task.Task;

import ml.ledv.library.cli.task.impl.*;
import ml.ledv.library.db.service.BookService;
import ml.ledv.library.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.LinkedList;

@Service
public class TaskHandler {

    private Task task;
    private BookService bookService;
    private UserService userService;

    private Deque<Task> stack;

    @Autowired
    public TaskHandler(final BookService bookService, final UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
        this.stack = new LinkedList<>();
    }

    public void addBook() {
        task = new AddBookTask(bookService);
        stack.push(task.execute());
    }

    public void deleteBook() {
        task = new DeleteBookTask(bookService);
        final Task executedTask = task.execute();
        if (executedTask != null) {
            stack.push(executedTask);
        } else return;
    }

    public void cancelBookReservation() {
        task = new CancelBookReservationTask(bookService, userService);
        final Task executedTask = task.execute();
        if (executedTask != null) {
            stack.push(executedTask);
        } else return;
    }

    public void showAllBooks() {
        task = new ShowAllBooksTask(bookService);
        stack.push(task.execute());
    }

    public void showAllFreBooks() {
        task = new ShowAllFreeBooksTask(bookService, userService);
        stack.push(task.execute());
    }

    public void reserveBook() {
        task = new ReserveBookTask(bookService, userService);
        final Task executedTask = task.execute();
        if (executedTask != null) {
            stack.push(executedTask);
        } else return;
    }

    public void undo() {
        if (!stack.isEmpty()) {
            stack.pop().undo();
        } else {
            System.out.println("Can't undo!");
        }
    }
}
